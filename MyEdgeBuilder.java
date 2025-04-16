 package myclassproject.mystorygraph;
 
 import static myclassproject.mystorygraph.MyStoryEntities.*;
 
 import java.util.List;
 
 //import com.actions.*;
 import com.playerInput.*;
 import com.playerInput.PlayerInteraction.Icons;
 //import com.sequences.CreateCharacterSequence;
 //import com.sequences.DialogSequence;
 //import com.sequences.NarrationSequence;
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
 		var choice = new PlayerInteraction(MyChoiceLabels.talkToMerlin.toString(), merlin, Icons.talk,
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
 		var choice1 = new PlayerInteraction(MyChoiceLabels.leaveCastle.toString(), greatHallDoor, Icons.exit, "Exit to the city");
 		var nextNode1 = get(MyNodeLabels.beggarQuest.toString());
 		node.add(new Edge(choice1, nextNode1));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void swordEdge() {
 		var node = get(MyNodeLabels.swordTool.toString());
 		var choice1 = new PlayerInteraction(MyChoiceLabels.leaveCastle.toString(), greatHallDoor, Icons.exit, "Exit to the city");
 		var nextNode1 = get(MyNodeLabels.beggarQuest.toString());
 		node.add(new Edge(choice1, nextNode1));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void compassEdge() {
 		var node = get(MyNodeLabels.compassTool.toString());
 		var choice1 = new PlayerInteraction(MyChoiceLabels.leaveCastle.toString(), greatHallDoor, Icons.exit, "Exit to the city");
 		var nextNode1 = get(MyNodeLabels.beggarQuest.toString());
 		node.add(new Edge(choice1, nextNode1));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void talkToBeggarEdge() {
 		var node = get(MyNodeLabels.beggarQuest.toString());
 		var choice1 = new PlayerInteraction(MyChoiceLabels.talkToBeggar.toString(), beggar, Icons.talk, "Talk to the beggar.");
 		var nextNode1 = get(MyNodeLabels.inTheCity.toString());
 		node.add(new Edge(choice1, nextNode1));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void specialQuestEdges() {
 		var node = get(MyNodeLabels.inTheCity.toString());
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
 		var choice1 = new PlayerInteraction(player, MyChoiceLabels.leaveCity.toString(), exitWestEnd);
 		var nextNode1 = get(MyNodeLabels.exitCity.toString());
 		node.add(new Edge(choice1, nextNode1));
 	}
 	
 	//Jackson
 	@BuilderMethod
 	public void listenEdges() {
 		var node = get(MyNodeLabels.accept.toString());
 		
 		var choice1 = new DialogChoice("Take his request");
 		var nextNode1 = get(MyNodeLabels.goToLibrary.toString());
 		node.add(new Edge(choice1, nextNode1));
 		
 		var choice2 = new DialogChoice("Continue your journey");
 		var nextNode2 = get(MyNodeLabels.continueJourney.toString());
 		node.add(new Edge (choice2, nextNode2));
 		
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void goToLibraryEdges() {
 		var node = get(MyNodeLabels.goToLibrary.toString());
 		
 		var choice1 = new PlayerInteraction(player, MyChoiceLabels.goToLibrary.toString(), exitWestEnd);
 		var nextNode1 = get(MyNodeLabels.findScroll.toString());
 		node.add(new Edge(choice1, nextNode1));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void questContEdges() {
 		var node = get(MyNodeLabels.findScroll.toString());
 		
 		var choice1 = new PlayerInteraction(MyChoiceLabels.grabScroll.toString(), libraryTable, Icons.hand, "Grab Scroll");
 		var nextNode1 = get(MyNodeLabels.leaveLibrary.toString());
 		node.add(new Edge(choice1, nextNode1));
 		
 	}
 	
 	//Jackson
 	@BuilderMethod
 	public void contJourneyEdges() {
 		var node = get(MyNodeLabels.continueJourney.toString());
 		var choice1 = new PlayerInteraction(player, MyChoiceLabels.leaveCity.toString(), exitWestEnd);
 		var nextNode1 = get(MyNodeLabels.exitCity.toString());
 		node.add(new Edge (choice1, nextNode1));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void leaveLibraryEdges() {
 		var node = get(MyNodeLabels.leaveLibrary.toString());
 		var choice1 = new PlayerInteraction(player, MyChoiceLabels.leaveLibrary.toString(), libraryDoor);
 		var nextNode1 = get(MyNodeLabels.hint.toString());
 		node.add(new Edge(choice1, nextNode1));
 	}
 	
 	//Jackson
 	@BuilderMethod
 	public void hintEdges() {
 		var node = get(MyNodeLabels.hint.toString());
 		var choice1 = new PlayerInteraction(player, MyChoiceLabels.leaveCity.toString(), exitWestEnd);
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
 	 	public void oldManRuinsEdges() {
 	 		var node = get(MyNodeLabels.toRuins.toString());
 	 		var choice1 = new PlayerInteraction(MyChoiceLabels.talkToOldMan.toString(), oldMan, Icons.talk, "Talk to the old man.");
 	 		var nextNode1 = get(MyNodeLabels.atRuins.toString());
 	 		node.add(new Edge(choice1, nextNode1));
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
 	
 	//Macey
 	@BuilderMethod
 	public void leaveRuinsEdges() {
 		var node = get(MyNodeLabels.rightRiddleAnswer.toString());
 		var choice1 = new PlayerInteraction(player, MyChoiceLabels.leaveRuins.toString(), ruinsDoor);
 		var nextNode1 = get(MyNodeLabels.toPort.toString());
 		node.add(new Edge(choice1, nextNode1));
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
 		
	 		var choice1 = new PlayerInteraction(MyChoiceLabels.talkToKnight.toString(), knight, Icons.talk, "Talk to the knight.");
	 		var nextNode1 = get(MyNodeLabels.fightk.toString());
	 		node.add(new Edge(choice1, nextNode1));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void knightChoiceEdge() {
 		var node = get(MyNodeLabels.fightk.toString());
 		
 		var choice1 = new DialogChoice ("Duel the knight");
 		var nextNode1 = get(MyNodeLabels.knightFight.toString());
 		node.add(new Edge (choice1, nextNode1));
 		
 		var choice2 = new DialogChoice("Retreat to the City");
 		var nextNode2 = get(MyNodeLabels.retreat1.toString());
 		node.add(new Edge (choice2, nextNode2));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void retreatToCity() {
 		var node = get(MyNodeLabels.retreat1.toString());
 		
 		var choice1 = new PlayerInteraction(player, MyChoiceLabels.goToCourtyard.toString(), campDoor);
 		var nextNode1 = get(MyNodeLabels.courtyard.toString());
 		node.add(new Edge (choice1, nextNode1));
 	}
 	
 	//Jackson
 	@BuilderMethod
 	public void duelKnight() {
 		var node = get(MyNodeLabels.knightFight.toString());
 		
 		//var choice1 = new DialogChoice ("Use my sword");
 		//var nextNode1 = get(MyNodeLabels.city2.toString());
 		//node.add(new Edge (choice1, nextNode1));
 		
 		var choice1 = new PlayerInteraction(player, "Use my sword", knight);
 		var nextNode1 = get(MyNodeLabels.toPortKnight.toString());
 		node.add(new Edge(choice1, nextNode1));
 		
 		var choice2 = new PlayerInteraction(player, "Use my compass", knight);
 		var nextNode2 = get(MyNodeLabels.death.toString());
 		node.add(new Edge (choice2, nextNode2));
 		
 		var choice3 = new PlayerInteraction(player, "Use my spellbook", knight);
 		var nextNode3 = get(MyNodeLabels.toPortKnight.toString());
 		node.add(new Edge (choice3, nextNode3));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void beatKnight() {
 		var node = get(MyNodeLabels.toPortKnight.toString());
 		
 		var choice1 = new PlayerInteraction(player, MyChoiceLabels.toPort.toString(), campDoor);
 		var nextNode1 = get(MyNodeLabels.port.toString());
 		node.add(new Edge (choice1, nextNode1));
 	}
 	
 	//Jackson
 	@BuilderMethod
 	public void rightForest() {
 		var node = get(MyNodeLabels.rightForest.toString());
 		
	 		var choice1 = new PlayerInteraction(MyChoiceLabels.talkToBandit.toString(), bandit, Icons.talk, "Talk to the bandit.");
	 		var nextNode1 = get(MyNodeLabels.fightb.toString());
	 		node.add(new Edge(choice1, nextNode1));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void banditChoiceEdge() {
 		var node = get(MyNodeLabels.fightb.toString());
 		
 		var choice1 = new DialogChoice ("Fight the bandit");
 		var nextNode1 = get(MyNodeLabels.banditFight.toString());
 		node.add(new Edge (choice1, nextNode1));
 		
 		var choice2 = new DialogChoice("Retreat to the City");
 		var nextNode2 = get(MyNodeLabels.retreat2.toString());
 		node.add(new Edge (choice2, nextNode2));
 	}
 	
 	//Jackson
 	@BuilderMethod
 	public void fightBandit() {
 		var node = get(MyNodeLabels.banditFight.toString());
 		
 		var choice1 = new PlayerInteraction(player, "Use my sword", bandit);
 		var nextNode1 = get(MyNodeLabels.toPortBandit.toString());
 		node.add(new Edge(choice1, nextNode1));
 		
 		var choice2 = new PlayerInteraction(player, "Use my compass", bandit);
 		var nextNode2 = get(MyNodeLabels.death.toString());
 		node.add(new Edge (choice2, nextNode2));
 		
 		var choice3 = new PlayerInteraction(player, "Use my spellbook", bandit);
 		var nextNode3 = get(MyNodeLabels.toPortBandit.toString());
 		node.add(new Edge (choice3, nextNode3));
 	}
 	
 	//Macey
 	 @BuilderMethod
 	 public void retreatToCity2() {
 	 	var node = get(MyNodeLabels.retreat2.toString());
 	 		
 	 	var choice1 = new PlayerInteraction(player, MyChoiceLabels.goToCourtyard.toString(), spookyPathWest);
 	 	var nextNode1 = get(MyNodeLabels.courtyard.toString());
 	 	node.add(new Edge (choice1, nextNode1));
 	 	}
 	 
  	//Macey
  	@BuilderMethod
  	public void beatBandit() {
  		var node = get(MyNodeLabels.toPortBandit.toString());
  		
  		var choice1 = new PlayerInteraction(player, MyChoiceLabels.toPort.toString(), spookyPathEast);
  		var nextNode1 = get(MyNodeLabels.port.toString());
  		node.add(new Edge (choice1, nextNode1));
  	}
 	
  //Macey
	 	@BuilderMethod
	 	public void portEdges() {
	 		var node = get(MyNodeLabels.toPort.toString());
	 		var choice1 = new PlayerInteraction(MyChoiceLabels.talkToWitch.toString(), witch, Icons.talk, "Talk to the witch.");
	 		var nextNode1 = get(MyNodeLabels.atPort.toString());
	 		node.add(new Edge(choice1, nextNode1));
	 	}
  	
  	//Macey + Jackson
 		@BuilderMethod
 	public void atPortEdges() {
 		var node = get(MyNodeLabels.atPort.toString());
 			
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
 			var node = get(MyNodeLabels.portRefusal.toString());
 					
 			var choice1 = new DialogChoice ("Swim");
 			var nextNode1 = get(MyNodeLabels.witchDeath.toString());
 			node.add(new Edge (choice1, nextNode1));
 				
 			var choice2 = new DialogChoice ("Boat");
 			var nextNode2 = get(MyNodeLabels.witchDeath.toString());
 			node.add(new Edge (choice2, nextNode2));
 	}
 	
 	 //Macey
 		@BuilderMethod
 		public void giveUpToolEdges() {
 			var node = get(MyNodeLabels.cottage.toString());
 			
 			var choice1 = new PlayerInteraction(MyChoiceLabels.goToCottage.toString(), farmDoor, Icons.exit, "Enter Cottage");
 			var nextNode1 = get(MyNodeLabels.atCottage.toString());
 			node.add(new Edge (choice1, nextNode1));
 		}
 		
 	//Jackson
 		@BuilderMethod
 		public void atCottageEdges() {
 			var node = get(MyNodeLabels.atCottage.toString());
 			
 			var choice1 = new DialogChoice ("Try the back door");
 			var nextNode1 = get(MyNodeLabels.backDoor.toString());
 			node.add(new Edge (choice1, nextNode1));
 			
 			var choice2 = new DialogChoice ("Try the front door");
 			var nextNode2 = get(MyNodeLabels.frontDoor.toString());
 			node.add(new Edge (choice2, nextNode2));
 		}
 		
 	//Jackson
 		@BuilderMethod
 		public void backDoorEdges() {
 			var node = get(MyNodeLabels.backDoor.toString());
 			
 			var choice1 = new PlayerInteraction(MyChoiceLabels.findScroll.toString(), scrollP, Icons.hand, "Read Scroll");
 			var nextNode1 = get(MyNodeLabels.inCottage.toString());
 			node.add(new Edge (choice1, nextNode1));
 		}
 		
 	 //Macey
 		@BuilderMethod
 		public void frontDoorEdges() {
 			var node = get(MyNodeLabels.frontDoor.toString());
 			
 			var choice1 = new PlayerInteraction(MyChoiceLabels.findScroll.toString(), scrollP, Icons.hand, "Read Scroll");
 			var nextNode1 = get(MyNodeLabels.inCottage.toString());
 			node.add(new Edge (choice1, nextNode1));
 		}
 		
 	 //Macey
 	 	/*@BuilderMethod
 	 	public void potionScrollEdge() {
 	 		var node = get(MyNodeLabels.potionScroll.toString());
 			
 			var choice1 = new PlayerInteraction(MyChoiceLabels.findScroll.toString(), scrollP, Icons.talk, "Read Scroll");
 			var nextNode1 = get(MyNodeLabels.inCottage.toString());
 			node.add(new Edge (choice1, nextNode1));
 	 	}*/
 		
 	 //Macey
 		@BuilderMethod
 		public void inCottageEdges() {
 			var node = get(MyNodeLabels.inCottage.toString());
 			
 			var choice1 = new PlayerInteraction(MyChoiceLabels.cottagePotions.toString(), redPotion, Icons.hand, "Red Potion");
 			var nextNode1 = get(MyNodeLabels.correctPotion.toString());
 			node.add(new Edge (choice1, nextNode1));
 			
 			var choice2 = new PlayerInteraction(MyChoiceLabels.cottagePotions.toString(), bluePotion, Icons.hand, "Blue Potion");
 			var nextNode2 = get(MyNodeLabels.wrongPotion1.toString());
 			node.add(new Edge (choice2, nextNode2));
 			
 			var choice3 = new PlayerInteraction(MyChoiceLabels.cottagePotions.toString(), greenPotion, Icons.hand, "Green Potion");
 			var nextNode3 = get(MyNodeLabels.wrongPotion2.toString());
 			node.add(new Edge (choice3, nextNode3));
 		}
 	
 	 //Macey
 	 	@BuilderMethod
 	 	public void correctPotion() {
 	 		var node = get(MyNodeLabels.correctPotion.toString());
 			
 	 		var choice1 = new PlayerInteraction(MyChoiceLabels.leaveCottage.toString(), cottageDoor, Icons.exit, "Exit Cottage");
 			var nextNode1 = get(MyNodeLabels.inDungeon.toString());
 			node.add(new Edge (choice1, nextNode1));
 	 	}
 	 	
 	 /*//Macey
 	 	@BuilderMethod
 	 	public void wrongPotion1() {
 	 		
 	 	}
 	 	
 	 //Macey
 	 	@BuilderMethod
 	 	public void wrongPotion2() {
 	 		
 	 	}*/
 	 	
 	 //Macey
 		@BuilderMethod
 		public void inDungeonEdges() {
 			var node = get(MyNodeLabels.inDungeon.toString());
 			
 			var choice1 = new PlayerInteraction(MyChoiceLabels.pickUpCoin.toString(), coin, Icons.coins, "Pick up coin!");
 			var nextNode1 = get(MyNodeLabels.findCoin.toString());
 			node.add(new Edge (choice1, nextNode1));
 		}
 		
 	//Jackson
 		@BuilderMethod
 		public void coinEdge() {
 			var node = get(MyNodeLabels.findCoin.toString());
 			
 			//var choice1 = new DialogChoice("Return to the Kingdom");
 			
 			var choice1 = new PlayerInteraction(player, MyChoiceLabels.returnToKingdom.toString(), dungeonDoor);
 			var nextNode1 = get(MyNodeLabels.returnKingdom.toString());
 			node.add(new Edge (choice1, nextNode1));
 		}
 		
 	//Macey
 		@BuilderMethod
 		public void merlinFinal() {
 			var node = get(MyNodeLabels.finalScene.toString());
 			
 			var choice1 = new PlayerInteraction(MyChoiceLabels.merlinFinalSpeech.toString(), merlin, Icons.talk, "Talk to Merlin.");
 			var nextNode1 = get(MyNodeLabels.merlinFinalSpeech.toString());
 			node.add(new Edge (choice1, nextNode1));
 		}
 	
 }


