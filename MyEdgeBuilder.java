
package myclassproject.mystorygraph;

import static myclassproject.mystorygraph.MyStoryEntities.*;

import java.util.List;

import com.actions.*;
import com.playerInput.*;
import com.playerInput.PlayerInteraction.Icons;
import com.sequences.CreateCharacterSequence;
import com.sequences.DialogSequence;
import com.sequences.NarrationSequence;
import com.storygraph.*;

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
		
		var root = get(MyNodeLabels.root.toString());
		var choice = new MenuChoice(MenuChoice.Options.Start);
		var nextNode = get(MyNodeLabels.atGreatHall.toString());
		root.add(new Edge(choice, nextNode));
	}
	
	//Macey
	@BuilderMethod
	public void atGreatHallLocationEdges() {
		var node = get(MyNodeLabels.atGreatHall.toString());
		var choice = new PlayerInteraction(MyNodeLabels.talkToMerlin.toString(), merlin, Icons.talk,
				"Talk to Merlin.");
		var nextNode = get(MyNodeLabels.merlinToolSpeech.toString());
		node.add(new Edge(choice, nextNode));
	}
	
	//Macey + Jackson
	@BuilderMethod
	public void startingToolEdges() {
		var node = get(MyNodeLabels.merlinToolSpeech.toString());
		var choice1 = new DialogChoice("Spellbook");
		var nextNode1 = get(MyNodeLabels.spellbookTool.toString());
		node.add(new Edge(choice1, nextNode1));

		var choice2 = new DialogChoice("Sword");
		var nextNode2 = get(MyNodeLabels.swordTool.toString());
		node.add(new Edge(choice2, nextNode2));
		
		var choice3 = new DialogChoice("Compass");
		var nextNode3 = get(MyNodeLabels.compassTool.toString());
		node.add(new Edge(choice3, nextNode3));
	}
	
	//Macey
	@BuilderMethod
	public void spellbookEdge() {
		var node = get(MyNodeLabels.spellbookTool.toString());
		var choice1 = new PlayerInteraction(player, MyChoiceLabels.leaveCastle.toString(), greatHallDoor);
		var nextNode1 = get(MyNodeLabels.beggarQuest.toString());
		node.add(new Edge(choice1, nextNode1));
	}
	
	//Macey
	@BuilderMethod
	public void swordEdge() {
		var node = get(MyNodeLabels.swordTool.toString());
		var choice1 = new PlayerInteraction(player, MyChoiceLabels.leaveCastle.toString(), greatHallDoor);
		var nextNode1 = get(MyNodeLabels.beggarQuest.toString());
		node.add(new Edge(choice1, nextNode1));
	}
	
	//Macey
	@BuilderMethod
	public void compassEdge() {
		var node = get(MyNodeLabels.compassTool.toString());
		var choice1 = new PlayerInteraction(player, MyChoiceLabels.leaveCastle.toString(), greatHallDoor);
		var nextNode1 = get(MyNodeLabels.beggarQuest.toString());
		node.add(new Edge(choice1, nextNode1));
	}
	
	//Macey
	@BuilderMethod
	public void specialQuestEdges() {
		var node = get(MyNodeLabels.beggarQuest.toString());
		var choice1 = new DialogChoice("Yes");
		var nextNode1 = get(MyNodeLabels.accept.toString());
		node.add(new Edge(choice1, nextNode1));
		
		var choice2 = new DialogChoice("No");
		var nextNode2 = get(MyNodeLabels.refuse.toString());
		node.add(new Edge(choice2, nextNode2));
	}
	
	//Jackson
	@BuilderMethod
	public void ignoreEdges() {
		var node = get(MyNodeLabels.refuse.toString());
		var choice1 = new PlayerInteraction(player, MyChoiceLabels.leaveCastle.toString(), exit);
		var nextNode1 = get(MyNodeLabels.exitCity.toString());
		node.add(new Edge(choice1, nextNode1));
	}
	
	//Jackson
	@BuilderMethod
	public void listenEdges() {
		var node = get(MyNodeLabels.accept.toString());
		
		var choice1 = new DialogChoice("Take his request");
		var nextNode1 = get(MyNodeLabels.acceptedRequest.toString());
		node.add(new Edge(choice1, nextNode1));
		
		var choice2 = new DialogChoice("Continue your journey");
		var nextNode2 = get(MyNodeLabels.continueJourney.toString());
		node.add(new Edge (choice2, nextNode2));
		
	}
	
	//Jackson
	@BuilderMethod
	public void questContEdges() {
		var node = get(MyNodeLabels.acceptedRequest.toString());
		
		var choice1 = new DialogChoice("Use spellbook to locate scroll");
		var nextNode1 = get(MyNodeLabels.hint.toString());
		node.add(new Edge (choice1, nextNode1));
		
		var choice2 = new DialogChoice("Use compass to locate scroll");
		node.add(new Edge (choice2, nextNode1));
		
		var choice3 = new DialogChoice("Search the shelves by hand");
		node.add(new Edge (choice3, nextNode1));
		
	}
	
	//Jackson
	@BuilderMethod
	public void contJourneyEdges() {
		var node = get(MyNodeLabels.continueJourney.toString());
		var choice1 = new DialogChoice("Exit");
		var nextNode1 = get(MyNodeLabels.exitCity.toString());
		node.add(new Edge (choice1, nextNode1));
	}
	
	//Macey
	@BuilderMethod
	public void findEdges() {
		var node = get(MyNodeLabels.findScroll.toString());
		var choice1 = new PlayerInteraction(player, MyChoiceLabels.grabScroll.toString(), libraryTable);
		var nextNode1 = get(MyNodeLabels.exitCity.toString());
		node.add(new Edge(choice1, nextNode1));
	}
	
	//Jackson
	@BuilderMethod
	public void hintEdges() {
		var node = get(MyNodeLabels.hint.toString());
		var choice1 = new PlayerInteraction(player, MyChoiceLabels.leaveCity.toString(), exit);
		var nextNode1 = get(MyNodeLabels.exitCity.toString());
		node.add(new Edge (choice1, nextNode1));
	}
	
	//Jackson
	@BuilderMethod
	public void exitEdges() {
		var node = get(MyNodeLabels.exitCity.toString());
		var choice1 = new DialogChoice("Ruins");
		var nextNode1 = get(MyNodeLabels.toRuins.toString());
		node.add(new Edge(choice1, nextNode1));

		var choice2 = new DialogChoice("Forest");
		var nextNode2 = get(MyNodeLabels.toForest.toString());
		node.add(new Edge(choice2, nextNode2));
		
		var choice3 = new DialogChoice("Port");
		var nextNode3 = get(MyNodeLabels.toPort.toString());
		node.add(new Edge(choice3, nextNode3));
		
		var choice4 = new DialogChoice("City");
		var nextNode4 = get(MyNodeLabels.toCity.toString());
		node.add(new Edge(choice4, nextNode4));

	}
	
	//Macey
	@BuilderMethod
	public void ruinsEdges() {
		var node = get(MyNodeLabels.atRuins.toString());
		var choice1 = new DialogChoice("N");
		var nextNode1 = get(MyNodeLabels.wrongRiddleAnswer.toString());
		node.add(new Edge(choice1, nextNode1));

		var choice2 = new DialogChoice("S");
		var nextNode2 = get(MyNodeLabels.wrongRiddleAnswer.toString());
		node.add(new Edge(choice2, nextNode2));
		
		var choice3 = new DialogChoice("E");
		var nextNode3 = get(MyNodeLabels.rightRiddleAnswer.toString());
		node.add(new Edge(choice3, nextNode3));
		
		var choice4 = new DialogChoice("W");
		var nextNode4 = get(MyNodeLabels.wrongRiddleAnswer.toString());
		node.add(new Edge(choice4, nextNode4));
	}
	
	//Jackson
	@BuilderMethod
	public void toForestEdges() {
		var node = get(MyNodeLabels.toForest.toString());
		
		var choice1 = new DialogChoice("Go left");
		var nextNode1 = get(MyNodeLabels.leftForest.toString());
		node.add(new Edge (choice1, nextNode1));
		
		var choice2 = new DialogChoice("Go right");
		var nextNode2 = get(MyNodeLabels.rightForest.toString());
		node.add(new Edge (choice2, nextNode2));
	}
	
	//Jackson
	@BuilderMethod
	public void leftForest() {
		var node = get(MyNodeLabels.leftForest.toString());
		
		var choice1 = new DialogChoice ("Duel the knight");
		var nextNode1 = get(MyNodeLabels.duel.toString());
		node.add(new Edge (choice1, nextNode1));
		
		var choice2 = new DialogChoice("Retreat to the City");
		var nextNode2 = get(MyNodeLabels.city2.toString());
		node.add(new Edge (choice2, nextNode2));
	}
	
	//Jackson
	@BuilderMethod
	public void duelKnight() {
		var node = get(MyNodeLabels.duel.toString());
		
		var choice1 = new DialogChoice ("Use my sword");
		var nextNode1 = get(MyNodeLabels.city2.toString());
		node.add(new Edge (choice1, nextNode1));
		
		var choice2 = new DialogChoice ("Use my compass");
		var nextNode2 = get(MyNodeLabels.death.toString());
		node.add(new Edge (choice2, nextNode2));
		
		var choice3 = new DialogChoice ("Use my spellbook");
		var nextNode3 = get(MyNodeLabels.city2.toString());
		node.add(new Edge (choice3, nextNode3));
	}
	
	//Jackson
	@BuilderMethod
	public void rightForest() {
		var node = get(MyNodeLabels.rightForest.toString());
		
		var choice1 = new DialogChoice ("Fight the bandit");
		var nextNode1 = get(MyNodeLabels.fight.toString());
		node.add(new Edge (choice1, nextNode1));
		
		var choice2 = new DialogChoice("Retreat to the City");
		var nextNode2 = get(MyNodeLabels.city2.toString());
		node.add(new Edge (choice2, nextNode2));
	}
	
	//Jackson
	@BuilderMethod
	public void fightBandit() {
		var node = get(MyNodeLabels.fight.toString());
		
		var choice1 = new DialogChoice ("Use my sword");
		var nextNode1 = get(MyNodeLabels.city2.toString());
		node.add(new Edge (choice1, nextNode1));
		
		var choice2 = new DialogChoice ("Use my compass");
		var nextNode2 = get(MyNodeLabels.death.toString());
		node.add(new Edge (choice2, nextNode2));
		
		var choice3 = new DialogChoice ("Use my spellbook");
		var nextNode3 = get(MyNodeLabels.city2.toString());
		node.add(new Edge (choice3, nextNode3));
	}
	
	//Jackson
	@BuilderMethod
	public void retreatToCity() {
		var node = get(MyNodeLabels.city2.toString());
		
		var choice1 = new DialogChoice ("Go to port");
		var nextNode1 = get(MyNodeLabels.port.toString());
		node.add(new Edge (choice1, nextNode1));
	}
	
	//Macey
		@BuilderMethod
	public void portEdges() {
		var node = get(MyNodeLabels.toPortEdge.toString());
			
		var choice1 = new DialogChoice ("Give up your tool");
		var nextNode1 = get(MyNodeLabels.cottage.toString());
		node.add(new Edge (choice1, nextNode1));
		
		var choice2 = new DialogChoice ("Refuse");
		var nextNode2 = get(MyNodeLabels.portRefusal.toString());
		node.add(new Edge (choice2, nextNode2));
	}
		
	//Macey
		@BuilderMethod
		public void portRefusalEdges() {
			var node = get(MyNodeLabels.toPortRefusalEdge.toString());
					
			var choice1 = new DialogChoice ("Swim");
			var nextNode1 = get(MyNodeLabels.witchDeath.toString());
			node.add(new Edge (choice1, nextNode1));
				
			var choice2 = new DialogChoice ("Boat");
			var nextNode2 = get(MyNodeLabels.witchDeath.toString());
			node.add(new Edge (choice2, nextNode2));
	}
	
	//Jackson
		@BuilderMethod
		public void giveUpToolEdges() {
			var node = get(MyNodeLabels.cottage.toString());
			
			var choice1 = new DialogChoice ("Try the window");
			var nextNode1 = get(MyNodeLabels.window.toString());
			node.add(new Edge (choice1, nextNode1));
			
			var choice2 = new DialogChoice ("Try the door");
			var nextNode2 = get(MyNodeLabels.guardDeath.toString());
			node.add(new Edge (choice2, nextNode2));
		}
		
	//Jackson
		@BuilderMethod
		public void windowEdges() {
			var node = get(MyNodeLabels.window.toString());
			
			var choice1 = new DialogChoice("Door one");
			var nextNode1 = get(MyNodeLabels.spellDeath.toString());
			node.add(new Edge (choice1, nextNode1));
			
			var choice2 = new DialogChoice("Door two");
			var nextNode2 = get(MyNodeLabels.findCoin.toString());
			node.add(new Edge (choice2, nextNode2));
			
			var choice3 = new DialogChoice("Door three");
			var nextNode3 = get(MyNodeLabels.guardDeath.toString());
			node.add(new Edge (choice3, nextNode3));
		}
		
	//Jackson
		@BuilderMethod
		public void coinEdge() {
			var node = get(MyNodeLabels.findCoin.toString());
			
			var choice1 = new DialogChoice("Return to the Kingdom");
			var nextNode1 = get(MyNodeLabels.returnKingdom.toString());
			node.add(new Edge (choice1, nextNode1));
		}
	
}

