ALTER TABLE "CORE_ROLE_RIGHT_XREF" DISABLE CONSTRAINT "FK5AFA2427E35D2FF0";
ALTER TABLE "CORE_ROLE_RIGHT_XREF" DISABLE CONSTRAINT "FK5AFA2427FEB2F584";
DELETE FROM core_role_right_xref WHERE right_id LIKE 'page.DownloadPage';
DELETE FROM core_right WHERE right_id LIKE 'page.DownloadPage';
DELETE FROM core_role_right_xref WHERE right_id LIKE 'general.DownloadBoxPanel';
DELETE FROM core_right WHERE right_id LIKE 'general.DownloadBoxPanel';
UPDATE core_role_right_xref SET right_id = 'download.author' WHERE right_id LIKE 'page.DownloadEditPage';
UPDATE core_right SET right_id = 'download.author', description = 'Download Author' WHERE right_id LIKE 'page.DownloadEditPage';
ALTER TABLE "CORE_ROLE_RIGHT_XREF" ENABLE CONSTRAINT "FK5AFA2427E35D2FF0";
ALTER TABLE "CORE_ROLE_RIGHT_XREF" ENABLE CONSTRAINT "FK5AFA2427FEB2F584";