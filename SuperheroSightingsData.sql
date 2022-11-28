USE SuperheroSightingsDB;

select * from superperson;
INSERT INTO superperson(`name`,`description`,superpower,is_hero) 
	VALUES("Kaio",
    "He loves his mom. He fights those of Boxmore, along with Radicles and Enid. Mr. Gar is his employer, the owner of Lakewood Plaza Turbo. He also has a best friend and classmate named Dendy.",
    "Strength Powermove",1);
INSERT INTO superperson(`name`,`description`,superpower,is_hero) 
	VALUES("Midoriya",
    "Nerd. Even though Izuku was born Quirkless, he manages to catch the attention of the legendary hero All Might, due to his innate heroism and a strong sense of justice, and has since become his close pupil, as well as a student in Class 1-A at U.A. High School. All Might passed on his transferable Quirk to Izuku, making him the ninth and current holder of One For All.",
    "One for all",1);
INSERT INTO superperson(`name`,`description`,superpower,is_hero) 
	VALUES("Professor Venomous","Professor Venomous is typically calm and indifferent in his interactions. However, he has a more vicious side beneath his cool exterior that he expresses during interactions such as fights with heroes. He has also repeatedly expressed his admiration towards Boxman's dedication to destroying the plaza.
    
    He has been shown to have a softer side to his personality and treats his minion Fink with great care and kindness, even going as far as to act as a parental figure to the young villain. Nevertheless, as in \"K.O. vs. Fink\", he begins to neglect her emotional needs due to a heavy and burdensome workload in addition to Shadowy's interference earlier in the episode, and fails to realize that his material compensation can’t satisfy her.
    
    The villain has shown to have the sentiments of a thrill-seeker. He has expressed boredom with the mundane course of his day to day life. He becomes exhilarated when becoming personally involved in attacking heroes. When he, Boxman, and Fink rode in the destructive vehicle Boxman built for Fink to attack the plaza, the situation ended in misadventure. Still though, Professor Venomous stated he likes being blown up and getting his hands dirty. The elation inspired him to buy back Boxmore from the Board Of Investors to give back to Boxman so more adventures in villainy can be had. Whether he fails or succeeds, Professor Venomous loves the experience of fighting heroes. His affinity for the villain lifestyle is so strong, he and Fink destroyed a planet given to him to conquer, later returning to Earth to finish what they started.
    
    Despite his villainous endeavors, he had made great strides to bond with his son, K.O., even attempting to help him defeat Shadowy Figure, before finding out they were the same person. Despite his efforts to bond with his son, his other self, as well as his fused persona don't really seem to care about K.O.'s feelings, as they used Carl's rampage to force K.O. to unleash T.K.O..
    
    After returning to his normal self, he is shown to be ashamed of his actions, as seen when he shows up to Boxman's house with a cake and apologizes. During the timeskip, Venomous (now married to Boxman) is seen being proud of Fink and her career as a professional gamer. His current relationship with K.O. is left unknown, as they are no longer seen interacting much after he regained control of his body.",
    "Science",0);
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
  