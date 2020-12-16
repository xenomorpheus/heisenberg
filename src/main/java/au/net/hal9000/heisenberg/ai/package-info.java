/**
 * Artificial Intelligence AKA Computational Search algorithms.
 *
 * <p>The eventual aim of this package is to allow Agents to move rationally within the game.
 *
 * @author Mike Bruins
 * @version 1.0
 *     <p>Action - A possible choice an Agent may make to change the state of the Model. e.g. move
 *     left.<br>
 *     Agent - the entity we control in the Model e.g. move the Agent towards the desired location.
 *     <p>Cost - A measure of the effort in performing the action. e.g. the distance of a step
 *     modified by gradient of ground.
 *     <p>Goal (State) - The desired State of the Model e.g. Agent at the desired destination.<br>
 *     (Intelligent) Agent (IA) is an autonomous entity which observes through sensors and acts upon
 *     an environment using actuators and directs its activity towards achieving goals.<br>
 *     Model - Agents think a simplified version of the world called the Model. Model should only
 *     contain the charateristics of the world that are relevant to the search.<br>
 *     A model is mutable.<br>
 *     Model State - A instantaneous snap-shot of the Model. <br>
 *     A ModelState should probably be immutable.<br>
 *     (Model State) Evaluator - A function to evaluate the State of the Model and give a value on
 *     how good the model is e.g. the effort to get from the current state to reach the Goal State.
 *     Evaluator function return zero at Goal State.<br>
 *     Path - an ordered list of Action objects.
 *     <p>Successor - An Action, Cost and the ModelState that action will lead to.<br>
 *     e.g. A movement left one step, a cost of 1.0 effort, and the new location which is to the
 *     left.
 *     <p>SuccessorFunction - A function that produces a list of Successor objects from current
 *     ModelState. <br>
 *     e.g. the function might produce a list of directions that may be stepped; each with the cost
 *     (effort) of each step and the new location reached.
 *     <p>TransitionFunction takes a Model State and applies the Action which results in another
 *     ModelState object.<br>
 *     e.g. The Agent takes a movement action from current position to a new position.
 */
package au.net.hal9000.heisenberg.ai;
