
Heisenberg:
===================

  [![Build Status](https://travis-ci.org/xenomorpheus/heisenberg.svg?branch=master)](https://travis-ci.org/xenomorpheus/heisenberg)


Project Aims:
===================
The aims of this project are somewhat unconventional:

1) To fulfil a long term goal of exploring AI programming in a MUD-like setting.
2) To have fun.
3) Develop my Java skills, in particular frameworks commonly used in the workplace.

Background and Inspiration:
============================

* I was inspired by text based MUDs of the 1990s in which
people's characters moved about in a text-based, DnD-styled
environment.  I thought one day it would be an interesting project 
to write an entity that could automatically move about the game 
environment and have some degree interaction with the players. 

* I wanted the interactions to be so much more than dumb 
automated responses. When the game players interacted with
the cat I wanted to impress, and perhaps entertain them.
I wanted to see if I could give the cat some AI.

* I wanted something simple and maybe even fun. I settled on a cat
as being the entity to control in the game setting.

* I also was looking for a vehicle to explore more aspects of Java.
I expected the project would need a lot of refactoring as components
were tried, therefore I wanted a language that allowed easy refactoring 
via IDE tools.  Game objects would typically be a sub-class of simpler objects.  

Project Components:
===================
Components have been progressively added as my java skills have improved
or I have identified solutions to the project's requirements.

* AI system - Computational search mechanisms to allow entities within the
  game to automatically plan and choose actions based on expected outcomes.
  The AI system can perceive the environment via hooks into the Physics engine.

* Crafting system - Provides everything from eating, carpentry, to spell 
  casting.  The ability to transform game elements into other game elements.
  The aim is to as much as possible have transformations defined in data,
  not hard coded.
 
* Physics Engine - Using the off the shelf JBox2d to provide an environment
  for game objects to move about in.

* DnD styled game objects - A small number of items typically found in a DnD 
  game.

* World Designer - Ability to place items within an environment.

* Character Sheet Designer - Ability to edit and equip player controlled entities.

* Emphasis on Quality - Extensive unit tests, code documentation and use of Checkstyle.

Project Technologies:
======================

* Maven
* JPA
* Apache EclipseLink - JPA Implementation, ORM
* Derby - Data store
* Log4j - Logging



