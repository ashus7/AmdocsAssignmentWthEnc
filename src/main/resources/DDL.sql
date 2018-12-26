DROP TABLE IF EXISTS 'am'.'book';
CREATE TABLE  'am'.'book' (
  'id' int(10) unsigned NOT NULL auto_increment,
  'title' varchar(45) NOT NULL,
  'author' varchar(45) NOT NULL,
  PRIMARY KEY  ('id')
) ENGINE=InnoDB DEFAULT CHARSET=latin1;