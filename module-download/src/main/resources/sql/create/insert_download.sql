INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('download.author','Download Author',{ts '2009-01-10 23:34:54.000'},'admin',{ts '2009-01-10 23:34:54.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('download.download','Download: Download as admin',{ts '2008-12-15 17:49:19.000'},'admin',{ts '2008-12-15 17:49:19.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('download.download.guest','Download: Download as guest',{ts '2008-12-15 17:49:34.000'},'admin',{ts '2008-12-15 17:49:34.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('download.download.registered','Download: Download as registered user',{ts '2008-12-15 17:49:53.000'},'admin',{ts '2008-12-15 17:49:53.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('download.view','Download: View as admin',{ts '2008-12-15 17:45:59.000'},'admin',{ts '2008-12-15 17:45:59.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('download.view.guest','Download: View as guest',{ts '2008-12-15 17:46:25.000'},'admin',{ts '2008-12-15 17:46:25.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('download.view.registered','Download: View as registered user',{ts '2008-12-15 17:46:59.000'},'admin',{ts '2008-12-15 17:46:59.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('download.vote','Download: Vote as admin',{ts '2008-12-26 01:36:19.000'},'admin',{ts '2008-12-26 01:36:19.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('download.vote.guest','Download: Vote as guest',{ts '2008-12-26 01:36:44.000'},'admin',{ts '2008-12-26 01:36:44.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('download.vote.registered','Download: Vote as registered user',{ts '2008-12-26 01:37:06.000'},'admin',{ts '2008-12-26 01:37:06.000'},'admin');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'download.download.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'download.download.registered');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'download.view');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'download.view.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'download.view.registered');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'download.vote');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'download.vote.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'download.vote.registered');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'download.author');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (2,'download.download.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (2,'download.view.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (2,'download.vote.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'download.view.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'download.view.registered');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'download.download.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'download.download.registered');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'download.vote.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'download.vote.registered');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('downloads_per_page','Downloads per page','Downloads','java.lang.Integer','5');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('download_vote_enabled','Vote enabled','Downloads','java.lang.Boolean','true');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('download_hide_broken','Hide broken downloads','Downloads','java.lang.Boolean','true');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('box_num_latest_downloads','Number of latest downloads','Downloads','java.lang.Integer','3');
INSERT INTO core_box (id,created_at,created_by,modified_at,modified_by,box_type,custom_style,content,sort,title,hide_title) VALUES (200,{ts '2009-01-05 12:18:01.000'},'admin',{ts '2009-01-05 12:47:42.000'},'Guest','DownloadBoxPanel','whiteBoxTemplate',null,7,'Latest Downloads Box',0);

INSERT INTO download (id,created_at,created_by,modified_at,modified_by,description,hits,number_of_votes,sum_of_rating,title,url,download_size,licence,manufacturer_homepage,price,software_version,manufacturer,broken) VALUES (1,{ts '2009-01-06 19:34:23.000'},'admin',{ts '2009-01-06 19:34:23.000'},'admin','<p>This is a sample. You can define download for http, https, ftp and local urls. Local urls starts with file:/path.<br />You can start a broken check and all broken downloads will marked as it.</p>',1,0,0,'Sample Download','/img/bg.gif',null,null,null,null,null,null,0);
INSERT INTO download_right_xref (download_id,right_id) VALUES (1,'download.view.guest');
INSERT INTO download_right_xref (download_id,right_id) VALUES (1,'download.view.registered');
INSERT INTO download_right_xref (download_id,right_id) VALUES (1,'download.download.registered');
INSERT INTO download_right_xref (download_id,right_id) VALUES (1,'download.download.guest');
INSERT INTO download_right_xref (download_id,right_id) VALUES (1,'download.vote.registered');
INSERT INTO download_right_xref (download_id,right_id) VALUES (1,'download.vote.guest');
INSERT INTO download_tag (tagname,created_at,created_by,modified_at,modified_by) VALUES ('sample',{ts '2009-01-06 19:28:56.000'},'admin',{ts '2009-01-06 19:28:56.000'},'admin');
INSERT INTO download_tag_xref (download_id,tagname) VALUES (1,'sample');

-- since 1.0-rc3
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('download_entries_in_feed','Download entries in feed','Downloads','java.lang.Integer','10');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('download_feed_title','Download feed title','Downloads','java.lang.String','Downloads');

