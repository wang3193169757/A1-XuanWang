PROG2004-2026-T5  Object Oriented Programming
Assessment 1 - Health Service Appointment System
Author: Xuan Wang (Student ID: 25259340)
=====================================================================

1. PROJECT STRUCTURE
---------------------------------------------------------------------
src/
  HealthProfessional.java   Abstract base class for every professional
                             (Part 1: encapsulation + abstraction)
  GeneralPractitioner.java   Subtype: general practitioner (Part 2)
  Specialist.java            Subtype: medical specialist (Part 2)
  Surgeon.java               Subtype: surgeon (Part 2)
  Nurse.java                 Subtype: nurse (Part 2)
  Patient.java               Patient name + mobile number (Part 4)
  ClinicSchedule.java        Fixed daily slots 08:00-16:00, 30 min (Part 5)
  Appointment.java           One appointment, Comparable by time (Part 4)
  AppointmentManager.java    Collection of appointments: add / cancel /
                              list, with double-booking protection (Part 5)
  Main.java                  Part 3 + Part 6: scripted end-to-end demo


2. HOW TO COMPILE AND RUN (Windows)
---------------------------------------------------------------------
Open a terminal in the project folder (the folder that contains src/),
then:

  javac -encoding utf8 -d out src\*.java
  java -cp out Main

(You need a JDK installed. The demo was compiled with OpenJDK 11.)


3. FOUR OOP PILLARS - WHERE THEY APPEAR (for the explanation video)
---------------------------------------------------------------------
* Encapsulation
  - All fields are private; access is through getters.
  - Constructors validate the data (numeric-only ID, non-blank name,
    valid mobile, valid slot) so an object cannot be created invalid.
  - Defensive copies protect the internal working-day list.

* Inheritance
  - GeneralPractitioner, Specialist, Surgeon and Nurse extend
    HealthProfessional and reuse its shared fields and code via super(...).
  - Each subtype adds its own fields and actions only.

* Polymorphism
  - All professionals are stored in one List<HealthProfessional> and
    printed through the common parent type; each object prints its own
    details via its overridden toString()/getSpecialty().
  - Appointment and AppointmentManager depend only on the abstract
    parent type, so they accept any professional subtype.

* Abstraction
  - HealthProfessional is abstract and cannot be instantiated directly;
    it declares the abstract method getSpecialty() that every concrete
    subtype must implement.


4. SUBMISSION CHECKLIST (from the Assessment brief)
---------------------------------------------------------------------
[ ] Rename the exported zip to "A1-<YourMySCUUsername>.zip"
    (e.g. if your username were lfolkman, the file would be
    A1-lfolkman.zip).
[ ] Push the project to GitHub; add the repository link in the comments
    (placeholders are already in HealthProfessional.java and Main.java).
    If the repo is private, invite your tutor and unit assessor.
[ ] Attach the WakaTime report/screenshots as A1-<username>-WakaTime.png
    (or .jpg / .pdf).
[ ] Add the short explanation video link in the comments.
[ ] Complete the GenAI declaration in the comments (tick one option and,
    if you used GenAI, describe which parts).


5. PLACEHOLDERS TO REPLACE BEFORE SUBMITTING
---------------------------------------------------------------------
- <PASTE YOUR GITHUB REPOSITORY LINK HERE>
- <PASTE YOUR SHORT VIDEO LINK HERE>
- GenAI declaration tick box and description
