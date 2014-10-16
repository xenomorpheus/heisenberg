Project Aims:
===================
The aims of this project are somewhat unconventional.

* I was inspired by text based MMOG of the 1990s in which
people's characters moved about in a text-based, DnD-styled
environment.  I thought one day it would be an interesting project 
to write an entity that could automatically move about the game 
environment and have some interaction with the players. 
I wanted something simple and maybe even fun. I settled on a cat.

* I wanted the interactions to be so much more than dumb 
automated responses. When the game players interacted with
the cat I wanted to impress, and perhaps entertain them.
I wanted to see if I could give the cat some AI.

* I wanted a vehicle to develop my Java programming skills. 
I wanted leverage proper Object Oriented concepts e.g. Game objects
would typically be a sub-class of simpler objects.

Project Components:
===================
Components have been progressively added as my java skills have improved
or I have identified solutions to the project's requirements.

* AI system - Computational search mechanisms to allow entities within the
  game to automatically plan and choose actions based on expected outcomes.
  The AI system can perceive the environment via hooks into the Physics engine.

* Crafting system - Provides mechanisms to transform game elements into
  other game elements. Everything from eating, carpentry, to spell casting. 
  The aim is to as much as possible have these processes data driven, not 
  require additional coding.

* Physics engine - Using the off the shelf JBox2d to provide an environment
  for game objects to move about in.

* DnD styled game objects - A small number of items typically found in a DnD 
  game.

* World Designer - Ability to place items within an environment.

* Emphasis on Quality - Extensive unit tests, code documentation and use of Checkstyle.

