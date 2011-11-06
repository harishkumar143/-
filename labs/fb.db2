PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE android_metadata (locale TEXT);
INSERT INTO "android_metadata" VALUES('en_US');
CREATE TABLE user_values (_id INTEGER PRIMARY KEY,name TEXT,value TEXT);
INSERT INTO "user_values" VALUES(1,'ringtone','true');
INSERT INTO "user_values" VALUES(2,'active_session_info','{"uid":647577191,"secret":"ac6cdf7eb71d86dae1d42d119bb1b1fa","username":"honesthenry@gmail.com","filter":"nf","access_token":"AAAAAUaZA8jlABAJVZC3XF1hATBr2PvexdlfaZBNjQ7ZB6op5vfJiuGz3jMukeEdaOBxzlW2T6KdDHFU9jRZCWS0Thnq6kVF8ZD","session_key":"90451f5fa4914f014090af50.0-647577191","profile":{"last_name":"Lee","uid":647577191,"first_name":"Henry Hm","pic_square":"http:\/\/profile.ak.fbcdn.net\/hprofile-ak-ash2\/274976_647577191_931918644_q.jpg","name":"Henry Hm Lee"}}');
CREATE TABLE notifications (_id INTEGER PRIMARY KEY,notif_id INT,app_id INT,sender_id INT,sender_name TEXT,sender_url TEXT,created INT,title TEXT,body TEXT,href TEXT,is_read INT,app_image TEXT,object_id TEXT,object_type TEXT);
CREATE TABLE user_statuses (_id INTEGER PRIMARY KEY,user_id INT,first_name TEXT,last_name TEXT,display_name TEXT,user_pic TEXT,timestamp INT,message TEXT);
CREATE TABLE mailbox_threads (_id INTEGER PRIMARY KEY,folder INT,tid INT,participants TEXT,subject TEXT,snippet TEXT,other_party INT,msg_count INT,unread_count INT,last_update INT,object_id INT);
CREATE TABLE mailbox_messages (_id INTEGER PRIMARY KEY,folder INT,tid INT,mid INT,author_id INT,sent INT,body TEXT);
CREATE TABLE mailbox_profiles (_id INTEGER PRIMARY KEY,id INT,display_name TEXT,profile_image_url TEXT,type INT);
CREATE TABLE connections (_id INTEGER PRIMARY KEY,user_id INT UNIQUE,display_name TEXT,connection_type INT NOT NULL DEFAULT 0,user_image_url TEXT,user_image BLOB,hash INT);
CREATE TABLE friends_data (_id INTEGER PRIMARY KEY,user_id INT UNIQUE,first_name TEXT,last_name TEXT,cell TEXT,other TEXT,email TEXT,birthday_month INT,birthday_day INT,birthday_year INT);
CREATE TABLE search_results (_id INTEGER PRIMARY KEY,user_id INT,display_name TEXT,user_image_url TEXT);
CREATE TABLE page_search_results (_id INTEGER PRIMARY KEY,page_id INT,display_name TEXT,pic TEXT);
CREATE TABLE default_page_images (_id INTEGER PRIMARY KEY,pic BLOB);
CREATE TABLE photos (_id INTEGER PRIMARY KEY,pid TEXT,aid TEXT,owner INT,src TEXT,src_big TEXT,src_small TEXT,caption TEXT,created INT,position INT,thumbnail BLOB,filename TEXT);
CREATE TABLE albums (_id INTEGER PRIMARY KEY,aid TEXT,cover_pid TEXT,cover_url TEXT,owner INT,name TEXT,created INT,modified INT,description TEXT,location TEXT,size INT,visibility TEXT,type TEXT,thumbnail BLOB,object_id INTEGER);
CREATE TABLE stream_photos (_id INTEGER PRIMARY KEY,url TEXT,filename TEXT);
CREATE TABLE events (_id INTEGER PRIMARY KEY,event_id INT,event_name TEXT,tagline TEXT,image_url TEXT,medium_image_url TEXT,host TEXT,description TEXT,event_type TEXT,event_subtype TEXT,start_time INT,end_time INT,creator_id INT,display_name TEXT,creator_image_url TEXT,location TEXT,venue BLOB,hide_guest_list INT,rsvp_status INT);
CREATE TABLE perf_sessions (_id INTEGER PRIMARY KEY,session_id INT,start_time INT,end_time INT,api_log TEXT);
CREATE TABLE key_value (_id INTEGER PRIMARY KEY,key TEXT UNIQUE,value TEXT);
INSERT INTO "key_value" VALUES(1,'last_run_build','5914');
CREATE TABLE chatmessages (_id INTEGER PRIMARY KEY,friend_uid INT,sent INT,timestamp INT,body TEXT);
CREATE TABLE chatconversations (_id INTEGER PRIMARY KEY,friend_uid INT,unread_count INT,unread_message TEXT);
CREATE TABLE cache (_id INTEGER PRIMARY KEY,name TEXT,value TEXT,timestamp INTEGER DEFAULT 0);
CREATE INDEX mailbox_threads_tid ON mailbox_threads(tid);
CREATE INDEX mailbox_profiles_id ON mailbox_profiles(id);
CREATE VIEW mailbox_messages_display AS SELECT mailbox_messages._id AS _id, mailbox_messages.mid AS mid, mailbox_messages.folder AS folder, mailbox_messages.tid AS tid, mailbox_messages.sent AS sent, mailbox_messages.body AS body, author.id as author_id, author.display_name as author_name, author.profile_image_url AS author_image_url, author.type AS author_type, object.id as object_id, object.display_name as object_name, object.profile_image_url AS object_image_url, object.type AS object_type FROM mailbox_messages LEFT OUTER JOIN mailbox_threads ON mailbox_messages.tid = mailbox_threads.tid AND mailbox_messages.folder = mailbox_threads.folder LEFT OUTER JOIN mailbox_profiles AS object ON mailbox_threads.object_id = object.id LEFT OUTER JOIN mailbox_profiles AS author ON mailbox_messages.author_id = author.id;
CREATE VIEW friends AS SELECT connections._id AS _id, connections.user_id AS user_id, connections.display_name AS display_name, connections.connection_type AS connection_type, connections.user_image_url AS user_image_url, connections.user_image AS user_image, connections.hash AS hash, friends_data.first_name AS first_name, friends_data.last_name AS last_name, friends_data.cell AS cell, friends_data.other AS other, friends_data.email AS email, friends_data.birthday_month AS birthday_month, friends_data.birthday_day AS birthday_day, friends_data.birthday_year AS birthday_year FROM connections LEFT OUTER JOIN friends_data ON connections.user_id=friends_data.user_id WHERE connections.connection_type=0;
CREATE INDEX CHAT_INDEX ON chatmessages ( friend_uid );
COMMIT;