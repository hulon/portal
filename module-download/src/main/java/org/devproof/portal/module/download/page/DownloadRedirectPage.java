/*
 * Copyright 2009-2011 Carsten Hufe devproof.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.devproof.portal.module.download.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.target.basic.RedirectRequestTarget;
import org.apache.wicket.request.target.resource.ResourceStreamRequestTarget;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.devproof.portal.core.app.PortalSession;
import org.devproof.portal.core.config.ModulePage;
import org.devproof.portal.core.module.common.util.FileResourceStream;
import org.devproof.portal.module.download.entity.Download;
import org.devproof.portal.module.download.service.DownloadService;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Carsten Hufe
 */
@ModulePage(mountPath = "/download", indexMountedPath = true)
public class DownloadRedirectPage extends WebPage {

    private static final long serialVersionUID = 1L;
    @SpringBean(name = "downloadService")
    private DownloadService downloadService;

    private PageParameters params;

    public DownloadRedirectPage(PageParameters params) {
        super(params);
        this.params = params;
    }

    @Override
    protected void onBeforeRender() {
        if (hasFirstParameter()) {
            Download download = downloadService.findById(getDownloadIdParam());
            if (hasDownloadRight(download)) {
                downloadService.incrementHits(download);
                if (isLocalFile(download)) {
                    startDownloadStream(download);
                } else {
                    redirectTo(download);
                }
            }
        }
        super.onBeforeRender();
    }

    private boolean isLocalFile(Download download) {
        return download.getUrl().startsWith("file:/");
    }

    private void startDownloadStream(Download download) {
        try {
            URI uri = new URI(download.getUrl());
            final File downloadFile = new File(uri);
            if (downloadFile.canRead()) {
                getRequestCycle().setRequestTarget(new ResourceStreamRequestTarget(new FileResourceStream(downloadFile)) {
                    @Override
                    public String getFileName() {
                        return downloadFile.getName();
                    }
                });
            }
        } catch (URISyntaxException e) {
            // do nothing
        } catch (FileNotFoundException e) {
            // do nothing
        }
    }

    private void redirectTo(Download download) {
        getRequestCycle().setRequestTarget(new RedirectRequestTarget(download.getUrl()));
    }

    private boolean hasDownloadRight(Download download) {
        if (download == null) {
            return false;
        }
        PortalSession session = (PortalSession) getSession();
        return session.hasRight("download.download", download.getDownloadRights());
    }

    private int getDownloadIdParam() {
        return params.getAsInteger("0", 0);
    }

    private boolean hasFirstParameter() {
        return params.containsKey("0");
    }
}
