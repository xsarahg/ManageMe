-- this file was originally named data.sql
-- this did not work because Hibernate needs the file to be named import.SQL

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_BUSINESS');
INSERT INTO roles(name) VALUES('ROLE_MEDIA');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

-- this used to give an error saying that the value for id could not be null
-- changed the generationtype of id to IDENTITY so the id would be created automatically
INSERT INTO categories(name) VALUES ('CATEGORY_ARTIST');

-- -- insert values artist roadmap template
INSERT INTO TEMPLATE_roadmaps (title, name, description, fases_amount) VALUES ('Artist roadmap', 'ARTIST', 'Follow this roadmap, and become a real artist!', '3');

INSERT INTO TEMPLATE_fases (title, description, subfases_amount, roadmap_template_id) VALUES ('EPIC', 'Getting started', '3', '1');
INSERT INTO TEMPLATE_fases (title, description, subfases_amount, roadmap_template_id) VALUES ('PROFESSIONAL DEVELOPMENT', 'Get professional!', '4', '1');
INSERT INTO TEMPLATE_fases (title, description, subfases_amount, roadmap_template_id) VALUES ('THE REAL DEAL', 'Get real!', '3', '1');

INSERT INTO TEMPLATE_roadmaps_fases (roadmap_template_id, fase_template_id) VALUES ('1', '1');
INSERT INTO TEMPLATE_roadmaps_fases (roadmap_template_id, fase_template_id) VALUES ('1', '2');
INSERT INTO TEMPLATE_roadmaps_fases (roadmap_template_id, fase_template_id) VALUES ('1', '3');

INSERT INTO TEMPLATE_subfases (title, description, condition, fase_template_id) VALUES ('Biography', 'If you want to succeed you need to learn how to introduce yourself, because you will be doing it a LOT. Go to your profile and add a biography there, then let one of your friends or family read it and ask for feedback. Use this feedback to write a better version. You can do this multiple times for an even better result.', 'UserInformation', '0');
INSERT INTO TEMPLATE_subfases (title, description, condition, fase_template_id) VALUES ('Link to portfolio', 'A portfolio is a must have in any creative industry, the music industry is no different. For a musician, youtube and soundcloud are excellent platforms to host your online portfolio. Create accounts for both if you dont have them already, and add links to them in your profile.', 'biography', '0');
INSERT INTO TEMPLATE_subfases (title, description, condition, fase_template_id) VALUES ('Upload pictures', 'Who wants to employ some faceless musician? No one, thats who. Adding pictures will add a face to the name and is an opportunity to show off your style. Go to your profile page to upload your best. If you have done some performances already you should definitely add pictures from those.', 'Genre', '0');

INSERT INTO TEMPLATE_subfases (title, description, condition, fase_template_id) VALUES ('Time Management', 'Its easy to lose track of time when youre doing something you enjoy, like making music. But sometimes time is best spend on other activities that might not be as much fun. Keeping track of your time and how you spend it is therefore vital to any successful artist. Go to the tutorials page and watch a video on Time Management.', 'Experience,2', '1');
INSERT INTO TEMPLATE_subfases (title, description, condition, fase_template_id) VALUES ('Writing', 'Now, hold on before you skip this one. Yes, maybe youre very good at writing lyrics, but thats not what this is about. Formulating sentences and having proper punctuation are really important, especially when you need to draft an official document to an organization. Watch a few tutorials on writing and practice writing an application.', 'Photo,2', '1');
INSERT INTO TEMPLATE_subfases (title, description, condition, fase_template_id) VALUES ('Personal Development', 'This is a vital skill for pretty much every profession. If you want to move forward its important to know what youre good at, but also what you suck at. Take a really good look at yourself and write down all the things you value about yourself and the things you believe to be your weak points. Get some friends or family to read them and ask for their opinion.', 'Photo,2', '1');
INSERT INTO TEMPLATE_subfases (title, description, condition, fase_template_id) VALUES ('Writing a CV', 'Even with an online portfolio, being able to write a CV is a vital skill. Watch the tutorial on CVs if you havent already, then write your CV.', 'Photo,2', '1');

INSERT INTO TEMPLATE_subfases (title, description, condition, fase_template_id) VALUES ('Upload Demos', 'Having links to your portfolio is a good step, but most people are lazy and wont even click a link without good reason. Upload some short demos (.mo3 format) to draw people in.', 'Track', '2');
INSERT INTO TEMPLATE_subfases (title, description, condition, fase_template_id) VALUES ('Profile', 'Check your profile again. Are there any things you would like to change, based on everything you have learned so far? Did you forget to add anything?', 'NewsItem', '2');
INSERT INTO TEMPLATE_subfases (title, description, condition, fase_template_id) VALUES ('EPK', 'This is it. This is what it has all been leading up to. Your very own Electronic Press Kit (EPK). Having one is an absolute must for any artist. Luckily for you, you are one of the first Manage Me users and therefore your EPK will be completely FREE. Send an email to mm.manageme@gmail.com with your username, telling us you want to receive your EPK, and we will use all data in your profile to create a professional EPK.', 'NewsItem', '2');

INSERT INTO TEMPLATE_fases_subfases (fase_template_id, subfase_template_id) VALUES ('1', '1');
INSERT INTO TEMPLATE_fases_subfases (fase_template_id, subfase_template_id) VALUES ('1', '2');
INSERT INTO TEMPLATE_fases_subfases (fase_template_id, subfase_template_id) VALUES ('1', '3');

INSERT INTO TEMPLATE_fases_subfases (fase_template_id, subfase_template_id) VALUES ('2', '4');
INSERT INTO TEMPLATE_fases_subfases (fase_template_id, subfase_template_id) VALUES ('2', '5');
INSERT INTO TEMPLATE_fases_subfases (fase_template_id, subfase_template_id) VALUES ('2', '6');
INSERT INTO TEMPLATE_fases_subfases (fase_template_id, subfase_template_id) VALUES ('2', '7');

INSERT INTO TEMPLATE_fases_subfases (fase_template_id, subfase_template_id) VALUES ('3', '8');
INSERT INTO TEMPLATE_fases_subfases (fase_template_id, subfase_template_id) VALUES ('3', '9');
INSERT INTO TEMPLATE_fases_subfases (fase_template_id, subfase_template_id) VALUES ('3', '10');


-- -- create fake tutorial (general and artist based)
INSERT INTO tutorials (link, title, description, category) VALUES ('https://youtu.be/blvcswxmPCU', 'Advice For Music Artists & Current State of Hip Hop!', 'Get some advise on becoming great at HipHop.', 'CATEGORY_ARTIST');


-- -- create fake quotes (general and artist based)
INSERT INTO quotes (author, text) VALUES ('John Lennon', 'Life is what happens when you are making other plans.');
INSERT INTO quotes (author, text) VALUES ('Beyonce', 'If everything was perfect, you would never learn and you would never grow.');
