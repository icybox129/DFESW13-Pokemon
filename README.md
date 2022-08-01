# DFESW13-Pokemon

Created by Nick Ciraki

## Table of Contents
- [Why am I doing this?](#why-am-i-doing-this)
- [How did I expect this challenge to go?](#how-did-i-expect-this-challenge-to-go)
- [What went well? What didn't go as planned?](#what-went-well-what-didnt-go-as-planned)
- [Possible improvments for future revisions of the project](#possible-improvements-for-future-revisions-of-the-project)
- [Postman Requests & Outputs](#postman-requests-and-outputs)
- [Jira Board](#jira-board)
- [Risk Assessment](#risk-assessment)
- [Instructions](#instructions)


## Why am I doing this?
This project is the culmination of my 9 week software development bootcamp with QA, utilising the skills I've learnt in Java and MySQL.\
The idea behind this project came from interest my in video games, but I also had inspiration from my nephew who had recenetly started\
playing the video game franchise himself.

## How did I expect this challenge to go?
I was confident going into this challenge, I felt like I had a good understanding of what was required of me. Throughout the bootcamp 
I felt like I got a good grasp of the skills being taught and could use them effectively on this project.

## What went well? What didn't go as planned?
Creating the basic CRUD functionality and custom queries went extremely well, I had pushed the commits for the CRUD functionality to their 
respective branches all within a couple of hours.However one mistake I did make was when I was creating my feature branches, I didn't merge back
into my 'dev' branch before branching off into a new feature branch. Instead I kept branching off the latest branch. I eventually corrected this by 
merging my latest branch back into dev and then merging the latest branch into older branches I wanted to revisit and update.

From the beginning of the project, aside from having CRUD functionality, I wanted the user to be able to input a specific pokemon, and providing
it was created in the database, it would return which move types would be effective against it. For example, if the user entered "Charmander" in
the GET request, it would return "Charmander is weak to: Water, Ground and Rock type moves!". Initially I had only planned to create type effectiveness 
for the original 151 pokemon, however that would've meant cherry picking which type combinations to include (many pokemon have dual types, Bulbasaur is
Grass/Poison) which would have been time consuming. So then I decided to create outcomes for every possible type combination, which there are over 180.
This resulted in a very long else IF statement and subsequent testing, because I had to create tests for every element of the else IF statement. On the
brightside, this particular function is now future-proofed!

## Possible improvements for future revisions of the project
The first major improvement would be to change my lengthy else IF statement for checking type effectiveness against a specific pokemon. I could have perhaps
used a switch case statement instead, which would have made it easier to read, however I was more comfortable with using IF statements at the time of
creating the project and wanted to deliver something that I knew would work. I had also done some research and possibly using data transfer objects
could be a more advanced solution. There are plenty more functions/methods I could add in the future that would utilise many characteristics of pokemon
that I did not include, such as movesets, evolutions, inividual stats etc.

## Postman Requests and Outputs

### Creating a pokemon entry into DB:
![createMon](https://i.imgur.com/XKNppWg.jpeg)

### Getting all entries in the DB:
![getAll](https://i.imgur.com/G9JWbXM.jpg)

### Getting a specific entry by ID:
![getById](https://i.imgur.com/RWiTw4k.jpg)

### Updating an entry by ID:
![updateById](https://i.imgur.com/aeYbNx3.jpg)

#### And the result:
![updateByIdResult](https://i.imgur.com/r7NfvQ2.jpg)

### Deleting an entry by ID:
Note: I switched from my production DB back to dev DB for the remainder of these screenshots, so the data entries may differ from the above screenshots
![deleteById](https://i.imgur.com/vwHzslT.jpg)

#### And the result:
![deleteByIdResult](https://i.imgur.com/E7URaeO.jpg)

### Deleting all entries
![deleteAll](https://i.imgur.com/0ZnyFS7.jpg)

#### And the result:
![deleteAllResult](https://i.imgur.com/Si33WhS.jpeg)

### Getting an entry by type:
![getType](https://i.imgur.com/Q4EGniq.jpeg)

### Getting an entry by name:
![getName](https://i.imgur.com/tkuxdpf.jpeg)

### Checking what move types are effective against a specific pokemon:
![checkType](https://i.imgur.com/adQKRDx.jpeg)

### Sorting all pokemon entries by their base stat number - Descending:
![bstDesc](https://i.imgur.com/LTBnY0h.jpeg)

### Sorting all pokemon entries by their base stat number - Ascending:
![bstAsc](https://i.imgur.com/RLfjAEV.jpeg)

### Sorting all pokemon entries by their national dex number - Descending:
![ndexDesc](https://i.imgur.com/gKbh6WT.jpeg)

### Sorting all pokemon entries by their national dex number - Ascending:
![ndexAsc](https://i.imgur.com/HxqG5UK.jpeg)

### Data being persistent in MySQL
![dataPersist](https://i.imgur.com/b8YJ72u.jpeg)

### Testing results, including coverage report. 99.1% Coverage:
![testing](https://i.imgur.com/3kaL9b8.jpeg)

## Jira Board
### Here is a link to my [Jira Board][jira-link]
[jira-link]: https://nickciraki.atlassian.net/jira/software/projects/PKMN/boards/1/roadmap

## Risk Assessment
![riskAssessment](https://i.imgur.com/ZvH1pSw.jpg)

## Instructions

#### Localhost port used in this project:
```
8129
```

#### Path Variables for all HTTP requests
Creating a pokemon:
```
localhost:8129/createMon
````
Get all pokemon from dB:
```
localhost:8129/getAll
```
Get a pokemon by an ID:
```
localhost:8129/getId/<ID number here>
```
Update a pokeon by an ID:
```
localhost:8129/updateById/<ID number here>
```
Deleting a pokemon by an ID:
```
localhost:8129/deleteById/<ID number here>
```
Deleting all pokemon in the database:
```
localhost:8129/deleteAll
```
Getting all pokemon by a type:
(Note: use %20 instead of spaces, e.g. Grass%20Poison)
```
localhost:8129/getType/Fire
localhost:8129/getType/Normal%20Flying
```
Getting a pokemon by name:
```
localhost:8129/getName/<name here>
```
Checking what move types are strong against a specific pokemon:
```
localhost:8129/checkType/<name here>
```
Sort all entries in descending order by base stats (BST):
```
localhost:8129/bstDesc
```
Sort all entries in ascending order by base stats (BST):
```
localhost:8129/bstAsc
```
Sort all entries in descending order by national dex number (ndex):
```
localhost:8129/ndexDesc
```
Sort all entries in ascending order order by national dex number (ndex):
```
localhost:8129/ndexAsc
```
