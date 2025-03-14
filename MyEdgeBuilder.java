package myclassproject.mystorygraph;

import static myclassproject.mystorygraph.MyStoryEntities.beggar;
import static myclassproject.mystorygraph.MyStoryEntities.city;
import static myclassproject.mystorygraph.MyStoryEntities.libraryDoor;
import static myclassproject.mystorygraph.MyStoryEntities.merlin;
import static myclassproject.mystorygraph.MyStoryEntities.scroll;
import static myclassproject.questexample.QuestStoryEntities.bandit;
import static myclassproject.questexample.QuestStoryEntities.cityDoor;
import static myclassproject.questexample.QuestStoryEntities.player;

import java.util.List;

import com.actions.DisableInput;
import com.actions.EnableInput;
import com.actions.Enter;
import com.actions.Exit;
import com.actions.Give;
import com.actions.HideDialog;
import com.actions.HideMenu;
import com.actions.Pickup;
import com.actions.Pocket;
import com.actions.SetPosition;
import com.actions.Unpocket;
import com.playerInput.*;
import com.playerInput.PlayerInteraction.Icons;
import com.sequences.CreateCharacterSequence;
import com.sequences.DialogSequence;
import com.sequences.NarrationSequence;
import com.storygraph.*;

import myclassproject.questexample.ChoiceLabels;
import myclassproject.questexample.NodeLabels;

public class MyEdgeBuilder extends NodeBuilder {
	/**
	 * Initializes the list of story graph nodes.
	 * @param list A list of all story graph nodes.
	 */
	public MyEdgeBuilder(List<Node> list) {
		super(list);
	}

	/**
	 * Write a method for each node. 
	 * Use get to get a reference to the node using its label.
	 * The method should add the edges of the node one by one. 
	 * These methods must have a BuilderMethod annotation.
	 */
	
	@BuilderMethod
	public void rootEdges() {
		//Example:
		//var root = get(NodeLabels.root.toString());
		//var choice = new MenuChoice(MenuChoice.Options.Start);
		//var nextNode = get(NodeLabels.atCottage.toString());
		//root.add(new Edge(choice, nextNode));
		
		var root = get(MyChoiceLabels.root.toString());
		var choice = new MenuChoice(MenuChoice.Options.Start);
		var nextNode = get(MyChoiceLabels.atGreatHall.toString());
		root.add(new Edge(choice, nextNode));
	}
	
	//Macey
	@BuilderMethod
	public void atGreatHallLocationEdges() {
		var node = get(MyChoiceLabels.atGreatHall.toString());
		var choice = new PlayerInteraction(ChoiceLabels.talkToMerlin.toString(), merlin, Icons.talk,
				"Talk to Merlin.");
		var nextNode = get(MyChoiceLabels.merlinToolSpeech.toString());
		node.add(new Edge(choice, nextNode));
	}
	
	//Macey + Jackson
	@BuilderMethod
	public void startingToolEdges() {
		var node = get(MyChoiceLabels.merlinToolSpeech.toString());
		var choice1 = new DialogChoice("Spellbook");
		var nextNode1 = get(MyChoiceLabels.beggarQuest.toString());
		node.add(new Edge(choice1, nextNode1));

		var choice2 = new DialogChoice("Sword");
		var nextNode2 = get(MyChoiceLabels.beggarQuest.toString());
		node.add(new Edge(choice2, nextNode2));
		
		var choice3 = new DialogChoice("Compass");
		var nextNode3 = get(MyChoiceLabels.beggarQuest.toString());
		node.add(new Edge(choice3, nextNode3));
	}
	
	//Macey
	@BuilderMethod
	public void specialQuestEdges() {
		var node = get(MyChoiceLabels.beggarQuest.toString());
		var choice1 = new DialogChoice("Yes");
		var nextNode1 = get(MyChoiceLabels.accept.toString());
		node.add(new Edge(choice1, nextNode1));
		
		var choice2 = new DialogChoice("No");
		var nextNode2 = get(MyChoiceLabels.refuse.toString());
		node.add(new Edge(choice2, nextNode2));
	}
	
	//Jackson
	@BuilderMethod
	public void ignoreEdges() {
		var node = get(MyChoiceLabels.refuse.toString());
		var choice1 = new DialogChoice("Exit");
		var nextNode1 = get(MyChoiceLabels.exitCity.toString());
		node.add(new Edge(choice1, nextNode1));
	}
	
	//Jackson
	@BuilderMethod
	public void listenEdges() {
		var node = get(MyChoiceLabels.accept.toString());
		
		var choice1 = new DialogChoice("Take his request");
		var nextNode1 = get(MyChoiceLabels.acceptedrequest.toString());
		node.add(new Edge(choice1, nextNode1));
		
		var choice2 = new DialogChoice("Continue your journey");
		var next Node2 = get(MyChoiceLabels.continuejourney.toString());
		node.add(new Edge (choice2, nextNode2));
		
	}
	
	//Jackson
	@BuilderMethod
	public void questContEdges() {
		var node = get(MyChoiceLabels.acceptedrequest.toString());
		
		var choice1 = new DialogChoice("Use spellbook to locate scroll");
		var nextNode1 = get(MyChoiceLabels.hint.toString());
		node.add(new Edge (choice1, nextNode1));
		
		var choice2 = new DialogChoice("Use compass to locate scroll");
		node.add(new Edge (choice2, nextNode1));
		
		var choice3 = new DialogChoice("Search the shelves by hand");
		node.add(new Edge (choice3, nextNode1));
		
	}
	
	//Jackson
	@BuilderMethod
	public void contJourneyEdges() {
		var node = get(myChoiceLabels.continuejourney.toString());
		var choice1 = new DialogChoice("Exit");
		var nextNode1 = get(MyChoiceLabels.exitCity.toString());
		node.add(new Edge (choice1, nextNode1));
	}
	
	@BuilderMethod
	public void spellbookFindEdges() {

	}
	
	@BuilderMethod
	public void compassFindEdges() {
		
	}
	
	@BuilderMethod
	public void regFindEdges() {

	}
	
	//Jackson
	@BuilderMethod
	public void hintEdges() {
		var node = get(myChoiceLabels.hint.toString());
		var choice1 = new DialogChoice("Exit");
		var nextNode1 = get(MyChoiceLabels.exitCity.toString());
		node.add(new Edge (choice1, nextNode1));
	}
	
	//Jackson
	@BuilderMethod
	public void exitEdges() {
		var node = get(MyChoiceLabels.exitCity.toString());
		var choice1 = new DialogChoice("Ruins");
		var nextNode1 = get(MyChoiceLabels.toRuins.toString());
		node.add(new Edge(choice1, nextNode1));

		var choice2 = new DialogChoice("Forest");
		var nextNode2 = get(MyChoiceLabels.toForest.toString());
		node.add(new Edge(choice2, nextNode2));
		
		var choice3 = new DialogChoice("Port");
		var nextNode3 = get(MyChoiceLabels.toPort.toString());
		node.add(new Edge(choice3, nextNode3));
		
		var choice4 = new DialogChoice("City");
		var nextNode4 = get(MyChoiceLabels.toCity.toString());
		node.add(new Edge(choice4, nextNode4));

	}
	
	//Jackson
	@BuilderMethod
	public void toForestEdges() {
		var node = get(MyChoiceLabels.toForest());
		
		var choice1 = new DialogChoice("Go left");
		var nextNode1 = get(MyChoiceLabels.leftForest.toString());
		node.add(new Edge (choice1, nextNode1));
		
		var choice2 = new DialogChoice("Go right");
		var nextNode2 = get(MyChoiceLabels.rightForest.toString());
		node.add(new Edge (choice2, nextNode2));
	}
	
	//Jackson
	@BuilderMethod
	public void leftForest() {
		var node = get(MyChoiceLabels.leftForest.toString());
		
		var choice1 = new DialogChoice ("Duel the knight");
		var nextNode1 = get(MyChoiceLabels.duel.toString());
		node.add(new Edge (choice1, nextNode1));
		
		var choice2 = new DialogChoice("Retreat to the City");
		var nextNode2 = get(MyChoiceLabels.city2.toString());
		node.add(new Edge (choice2, nextNode2));
	}
	
	//Jackson
	@BuilderMethod
	public void duelKnight() {
		var node = get(MyChoiceLabels.duel.toString());
		
		var choice1 = new DialogChoice ("Use my sword");
		var nextNode1 = get(MyChoiceLabels.city2.toString());
		node.add(new Edge (choice1, nextNode1));
		
		var choice2 = new DialogChoice ("Use my compass");
		var nextNode2 = get(MyChoicelabels.death.toString());
		node.add(new Edge (choice 2, nextNode2))
		
		var choice3 = new DialogChoice ("Use my spellbook");
		node.add(new Edge (choice 3, nextNode1));
	}
	
	//Jackson
	@BuilderMethod
	public void rightForest() {
		var node = get(MyChoiceLabels.rightForest.toString());
		
		var choice1 = new DialogChoice ("Fight the troll");
		var nextNode1 = get(MyChoiceLabels.fight.toString());
		node.add(new Edge (choice1, nextNode1));
		
		var choice2 = new DialogChoice("Retreat to the City");
		var nextNode2 = get(MyChoiceLabels.city2.toString());
		node.add(new Edge (choice2, nextNode2));
	}
	
	//Jackson
	@BuilderMethod
	public void fightTroll() {
		var node = get(MyChoiceLabels.fight.toString());
		
		var choice1 = new DialogChoice ("Use my sword");
		var nextNode1 = get(MyChoiceLabels.city2.toString());
		node.add(new Edge (choice1, nextNode1));
		
		var choice2 = new DialogChoice ("Use my compass");
		var nextNode2 = get(MyChoicelabels.death.toString());
		node.add(new Edge (choice 2, nextNode2))
		
		var choice3 = new DialogChoice ("Use my spellbook");
		node.add(new Edge (choice 3, nextNode1));
	}
	
	//Jackson
	@BuilderMethod
	public void retreatToCity() {
		var node = get(MyChoiceLabels.city2.toString());
		
		var choice1 = new DialogChoice ("Go to port");
		var nextNode1 = get(MyChoiceLabels.port.toString());
		node.add(new Edge (choice1, nextNode1));
	}
}
