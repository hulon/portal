CREATE TABLE article
(
   id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),
   created_at timestamp,
   created_by varchar(30),
   modified_at timestamp,
   modified_by varchar(30),
   content_id varchar(255),
   teaser longvarchar,
   title varchar(255),
   PRIMARY KEY(id)
)
;
CREATE TABLE article_page
(
   content_id varchar(255) NOT NULL,
   page int NOT NULL,
   content longvarchar,
   article_id int NOT NULL,
   PRIMARY KEY (content_id,page)
)
;
CREATE TABLE article_right_xref
(
   article_id int NOT NULL,
   right_id varchar(50) NOT NULL
)
;
CREATE TABLE article_tag
(
   tagname varchar(255),
   created_at timestamp,
   created_by varchar(30),
   modified_at timestamp,
   modified_by varchar(30),
   PRIMARY KEY(tagname)
)
;
CREATE TABLE article_tag_xref
(
   article_id int NOT NULL,
   tagname varchar(255) NOT NULL
)
;