USE SuperheroSightingsDB;
TRUNCATE TABLE organization_superperson;
TRUNCATE TABLE superperson;
TRUNCATE TABLE `organization`;
TRUNCATE TABLE location;
TRUNCATE TABLE sighting;



select * from superperson;
INSERT INTO superperson(`name`,`description`,superpower,is_hero) 
	VALUES("Kaio","He loves his mom","Strength Powermove",1);
INSERT INTO superperson(`name`,`description`,superpower,is_hero) 
	VALUES("Midoriya","Nerd","One for all",1);
INSERT INTO superperson(`name`,`description`,superpower,is_hero) 
	VALUES("Professor Venomous","Good Dad","Science",0);
INSERT INTO superperson(`name`,`description`,superpower,is_hero) 
	VALUES("Boxman","Wants to destroy plaza","Robots", 0);
INSERT INTO superperson(`name`,`description`,superpower) 
	VALUES("Deadpool","hilarious","Regeneration");

select * from `organization`;
INSERT INTO `organization`(`name`,`description`,address,phone,email) 
	VALUES ("Doofinshmertz Evil inc","Take over the tristate area", "180-evil blvd","8005555555","3v1l1sc001@gmail.com");
INSERT INTO `organization`(`name`,`description`,address,phone,email) 
	VALUES ("OWCA","Protect the World", "GoodGuys 7th ave","8007777777","3v1l1sb4d@gmail.com");
INSERT INTO `organization`(`name`,`description`,address,phone,email) 
	VALUES ("UA","Train Heros", "Osaka Japan","8005666666","ForTheFuture@gmail.com");
    
#MAKING LOCATIONS
select * from location;
INSERT INTO location(`name`,`description`,`address`,`lattitude`,`longitude`)
	VALUES("The Rock","it's big","the rock ave",242.32,12.32);
    
INSERT INTO location(`name`,`description`,`address`,`lattitude`,`longitude`)
	VALUES("Bikini Bottom","very wet","under the sea",48.232,82.25);
INSERT INTO location(`name`,`description`,`address`,`lattitude`,`longitude`)
	VALUES("Queens","Getouttahere","Jamaica Center",747.1,92.2);    
INSERT INTO location(`name`,`description`,`address`,`lattitude`,`longitude`)
	VALUES("Times Square","The big apple","64th ave",242,94.2);
INSERT INTO location(`name`,`description`,`address`,`lattitude`,`longitude`)
	VALUES("Jersey","Everything is legal","New Jersey",82.2,1.01);
INSERT INTO location(`name`,`description`,`address`,`lattitude`,`longitude`)
	VALUES("Newtopia","Newt Land","New Newtopia",72.32,11.32);
INSERT INTO location(`name`,`description`,`address`,`lattitude`,`longitude`)
	VALUES("The bubble bowl","best place to play","Football Stadium",22.3,30.1);
INSERT INTO location(`name`,`description`,`address`,`lattitude`,`longitude`)
	VALUES("Fairy Land","Has Fairygod parents","Accross the rainbow",142.23,12.1);
INSERT INTO location(`name`,`description`,`address`,`lattitude`,`longitude`)
	VALUES("The Rock","it's big","the rock ave",242.32,12.32);
INSERT INTO location(`name`,`description`,`address`,`lattitude`,`longitude`)
	VALUES("Retroville","Normal Town","828th ave",542.32,122.32);
INSERT INTO location(`name`,`description`,`address`,`lattitude`,`longitude`)
	VALUES("Kanto","for newbs","kanto st",23.2,123.2);
INSERT INTO location(`name`,`description`,`address`,`lattitude`,`longitude`)
	VALUES("Peoria","Safe only at bradley","160 callendar",22.12,32);
    
select * from sighting;
# Max Numbers (12,5,doesn't matter)
INSERT INTO sighting (location_id,superperson_id,sighting_time)
	VALUES(1,5,"2014-05-007");
INSERT INTO sighting (location_id,superperson_id,sighting_time)
	VALUES(10,4,"2015-01-007");
INSERT INTO sighting (location_id,superperson_id,sighting_time)
	VALUES(3,1,"2017-08-007");
INSERT INTO sighting (location_id,superperson_id,sighting_time)
	VALUES(9,3,"2017-10-007");
INSERT INTO sighting (location_id,superperson_id,sighting_time)
	VALUES(5,5,"2019-01-007");
INSERT INTO sighting (location_id,superperson_id,sighting_time)
	VALUES(4,3,"2019-02-007");
INSERT INTO sighting (location_id,superperson_id,sighting_time)
	VALUES(3,4,"2019-11-007");
INSERT INTO sighting (location_id,superperson_id,sighting_time)
	VALUES(8,3,"2019-12-007");
INSERT INTO sighting (location_id,superperson_id,sighting_time)
	VALUES(5,5,"2020-10-007");
INSERT INTO sighting (location_id,superperson_id,sighting_time)
	VALUES(2,2,"2022-02-007");
INSERT INTO sighting (location_id,superperson_id,sighting_time)
	VALUES(4,2,"2022-05-007");
  
#max num (3,5)
select * from organization_superperson;
INSERT INTO organization_superperson(organization_id,superperson_id)
	VALUES(2,1);
INSERT INTO organization_superperson(organization_id,superperson_id)
	VALUES(3,4);
INSERT INTO organization_superperson(organization_id,superperson_id)
	VALUES(3,3);
INSERT INTO organization_superperson(organization_id,superperson_id)
	VALUES(1,1);
INSERT INTO organization_superperson(organization_id,superperson_id)
	VALUES(2,2);
INSERT INTO organization_superperson(organization_id,superperson_id)
	VALUES(1,3);
  