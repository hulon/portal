INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('page.BookmarkEditPage','Bookmark Author: Edit bookmarks',{ts '2009-01-10 23:33:51.000'},'admin',{ts '2009-01-10 23:33:51.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('bookmark.view','Bookmark: View as admin',{ts '2008-12-15 17:53:49.000'},'admin',{ts '2008-12-15 17:53:49.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('bookmark.view.guest','Bookmark: View as guest',{ts '2008-12-15 17:54:16.000'},'admin',{ts '2008-12-15 17:54:16.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('bookmark.view.registered','Bookmark: View as registered user',{ts '2008-12-15 17:54:39.000'},'admin',{ts '2008-12-15 17:54:39.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('bookmark.visit','Bookmark: Visit as admin',{ts '2008-12-15 17:55:14.000'},'admin',{ts '2008-12-15 17:55:14.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('bookmark.visit.guest','Bookmark: Visit as guest',{ts '2008-12-15 17:55:31.000'},'admin',{ts '2008-12-15 17:55:31.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('bookmark.visit.registered','Bookmark: Visit as registered user',{ts '2008-12-15 17:55:51.000'},'admin',{ts '2008-12-15 17:55:51.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('bookmark.vote','Bookmark: Vote as admin',{ts '2008-12-29 19:53:07.000'},'admin',{ts '2008-12-29 19:53:07.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('bookmark.vote.guest','Bookmark: Vote as guest',{ts '2008-12-29 19:53:32.000'},'admin',{ts '2008-12-29 19:53:32.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('bookmark.vote.registered','Bookmark: Vote as registered user',{ts '2008-12-29 19:53:58.000'},'admin',{ts '2008-12-29 19:53:58.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('general.BookmarkBoxPanel','See Box: Latest bookmarks box',{ts '2009-01-05 14:20:01.000'},'admin',{ts '2009-01-05 14:20:01.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('page.BookmarkPage','Bookmark: See the bookmarks',{ts '2009-01-05 23:30:05.000'},'admin',{ts '2009-01-05 23:30:38.000'},'admin');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'page.BookmarkPage');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'bookmark.view');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'bookmark.view.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'bookmark.view.registered');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'bookmark.visit');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'bookmark.visit.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'bookmark.visit.registered');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'bookmark.vote');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'bookmark.vote.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'bookmark.vote.registered');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'general.BookmarkBoxPanel');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'page.BookmarkEditPage');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (2,'bookmark.view.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (2,'bookmark.visit.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (2,'bookmark.vote.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (2,'general.BookmarkBoxPanel');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (2,'page.BookmarkPage');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'bookmark.view.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'bookmark.view.registered');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'bookmark.visit.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'bookmark.visit.registered');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'bookmark.vote.guest');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'bookmark.vote.registered');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'general.BookmarkBoxPanel');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'page.BookmarkPage');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('bookmarks_per_page','Bookmarks per page','Bookmarks','java.lang.Integer','5');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('bookmark_vote_enabled','Vote enabled','Bookmarks','java.lang.Boolean','true');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('bookmark_hide_broken','Hide broken bookmarks','Bookmarks','java.lang.Boolean','true');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('box_num_latest_bookmarks','Number of latest bookmarks','Bookmarks','java.lang.Integer','3');
INSERT INTO core_box (id,created_at,created_by,modified_at,modified_by,box_type,content,sort,title) VALUES (11,{ts '2009-01-05 12:18:22.000'},'admin',{ts '2009-01-05 12:47:42.000'},'Guest','BookmarkBoxPanel',null,9,'Latest Bookmarks Box');

INSERT INTO bookmark (id,created_at,created_by,modified_at,modified_by,description,hits,number_of_votes,sum_of_rating,title,url,source,broken,sync_hash,sync_username) VALUES (1,{ts '2009-01-06 19:35:49.000'},'admin',{ts '2009-01-06 19:35:49.000'},'admin','<p>This a sample bookmark and refers to devproof.org. </p>',0,0,0,'Sample Bookmark','http://devproof.org','MANUAL',0,null,null);

INSERT INTO bookmark_right_xref (bookmark_id,right_id) VALUES (1,'bookmark.view.guest');
INSERT INTO bookmark_right_xref (bookmark_id,right_id) VALUES (1,'bookmark.view.registered');
INSERT INTO bookmark_right_xref (bookmark_id,right_id) VALUES (1,'bookmark.visit.guest');
INSERT INTO bookmark_right_xref (bookmark_id,right_id) VALUES (1,'bookmark.visit.registered');
INSERT INTO bookmark_right_xref (bookmark_id,right_id) VALUES (1,'bookmark.vote.guest');
INSERT INTO bookmark_right_xref (bookmark_id,right_id) VALUES (1,'bookmark.vote.registered');
INSERT INTO bookmark_tag (tagname,created_at,created_by,modified_at,modified_by) VALUES ('devproof',{ts '2009-01-06 19:28:56.000'},'admin',{ts '2009-01-06 19:28:56.000'},'admin');
INSERT INTO bookmark_tag (tagname,created_at,created_by,modified_at,modified_by) VALUES ('sample',{ts '2009-01-06 19:28:56.000'},'admin',{ts '2009-01-06 19:28:56.000'},'admin');
INSERT INTO bookmark_tag_xref (bookmark_id,tagname) VALUES (1,'devproof');
INSERT INTO bookmark_tag_xref (bookmark_id,tagname) VALUES (1,'sample');

-- since 1.0-rc3
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('bookmark_entries_in_feed','Bookmark entries in feed','Bookmarks','java.lang.Integer','10');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('bookmark_feed_title','Bookmark feed title','Bookmarks','java.lang.String','Bookmarks');
