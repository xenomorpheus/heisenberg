/**

Artificial Intelligence AKA Computational Search algorithms.

The eventual aim of this package is to allow Items to rationally move within the game.

@author Mike Bruins
@version 1.0

Action - A possible choice an Agent may make to change the state of the Model. e.g. move left.<br>

Goal - The desired State of the Model.<br>

(Intelligent) Agent (IA) is an autonomous entity which observes through sensors and acts upon an environment 
     using actuators and directs its activity towards achieving goals.<br>

Model - Agents think a simplified version of the world called the Model. Model should only contain the relevant aspects of the world.<br>
    A model is mutable.<br>

Model State - The instantaneous representation of a Model. A ModelState should be (considered) immutable.<br>

(Model State) Evaluator - A function to evaluate the State of the Model and give a value on how close the state is to the Goal State. 
Evaluator function return zero at Goal State.<br>

Path - an ordered list of Action objects.

Successor - A  of an Action and the ModelState that action will lead to.<br>

SuccessorFunction - A function that produces a list of Successor objects from current ModelState. <br>

TransitionFunction takes a Model State and applies the Action which results in another ModelState object.<br>

 **/

package au.net.hal9000.heisenberg.ai;