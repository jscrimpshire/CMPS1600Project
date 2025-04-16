package myclassproject.mystorygraph;
 
 import static myclassproject.mystorygraph.MyStoryEntities.*;
 
 import java.util.List;
 
 import com.storygraph.*;
 import com.actions.*;
 import com.sequences.*;
 
 public class MyNodeBuilder extends NodeBuilder {
 	public MyNodeBuilder(List<Node> list) {
 		super(list);
 	}
 
 	/**
 	 * Write a method for each node. 
 	 * Use get to get a reference to the node using its label.
 	 * The method adds Camelot actions that execute in order when visiting that node. 
 	 * These methods must have a BuilderMethod annotation.
 	 */
 	
 	//Macey
 	@BuilderMethod
 	public void rootActions() {
 		//Example:
 		//var root = get(NodeLabels.root.toString());
 		//root.add(new CreateAll(List.of(cottage, town, sword)));
 		
 		var root = get(MyNodeLabels.root.toString());
 		root.add(new CreateAll(List.of(greatHall, sword, spellBook, compass, city, scroll, library,
 				ruins, port, forest, camp, spookyPath, courtyard, cottage, farm, scrollP, coin, 
 				redPotion, bluePotion, greenPotion, dungeon)))
 		.add(new CreateCharacterSequence(player))
 		.add(new SetPosition(player, greatHall))
 		.add(new CreateCharacterSequence(merlin))
 		.add(new SetPosition(merlin, greatHallThrone))
 		.add(new SetCameraFocus(player))
 		.add(new ShowMenu());
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void atGreatHallLocation() {
 		var node = get(MyNodeLabels.atGreatHall.toString());
 		node.add(new HideMenu()).add(new EnableInput());
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void startingTool() {
 		var node = get(MyNodeLabels.merlinToolSpeech.toString());
 		node.add(new DialogSequence(player, merlin, 
 				List.of("The Coin of Camelot is in peril. Choose your starting tool for the journey."),
 				List.of("SpellBook", "Sword", "Compass")));
 	}
 	
 
 	//Macey
 	@BuilderMethod
 	public void spellbook() {
 		var node = get(MyNodeLabels.spellbookTool.toString());
 		node.add(new HideDialog())
 			.add(new Draw(merlin, spellBook))
 			.add(new Take(player, spellBook, merlin))
 			.add(new Pocket(player, spellBook));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void sword() {
 		var node = get(MyNodeLabels.swordTool.toString());
 		node.add(new HideDialog())
 			.add(new Draw(merlin, sword))
 			.add(new Take(player, sword, merlin))
 			.add(new Pocket(player, sword));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void compass() {
 		var node = get(MyNodeLabels.compassTool.toString());
 		node.add(new HideDialog())
 			.add(new Draw(merlin, compass))
 			.add(new Take(player, compass, merlin))
 			.add(new Pocket(player, compass));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void specialQuest() {
 		var node = get(MyNodeLabels.beggarQuest.toString());
 		node.add(new DisableInput()).add(new Exit(player, greatHallDoor, true)).add(new Enter(player, cityDoor, true)).add(new EnableInput());
 		node.add(new CreateCharacterSequence(beggar))
 			.add(new SetPosition(beggar, cityBench));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void talkToBeggar() {
 		var node = get(MyNodeLabels.inTheCity.toString());
		node.add(new DialogSequence(player, beggar, 
				List.of("Please help me."),
				List.of("Yes", "No")));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void ignore() {
 		var node = get(MyNodeLabels.refuse.toString());
 		node.add(new HideDialog());
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void listen() {
 		var node = get(MyNodeLabels.accept.toString());
 		node.add(new HideDialog()).add(new DialogSequence(player, beggar, 
 				List.of("I used to be a guard in the Kingdom. After the disappearance of the coin, I was let go. I lost my home and have been living on the streets since. Inside the castle, I hid a scroll. Somewhere in the library, it lies. It would prove my innocence. Please retrieve it so I can get back on my feet"), 
 				List.of("Take his request", "Continue your journey")));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void questCont() {
 		var node = get(MyNodeLabels.goToLibrary.toString());
 		node.add(new HideDialog()).add(new DisableInput()).add(new Exit(player, cityDoor, true)).add(new Enter(player, libraryDoor, true))
 		//.add(new SetPosition(player, library))	
 		.add(new SetPosition(scroll, libraryTable))	
 		.add(new EnableInput());
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void regFind() {
 		var node = get(MyNodeLabels.findScroll.toString());
 		node.add(new Pickup(player, scroll));
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void leaveLibrary() {
 		var node = get(MyNodeLabels.leaveLibrary.toString());
 		node.add(new DisableInput()).add(new HideDialog()).add(new Exit(player, libraryDoor, true)).add(new Enter(player, cityDoor, true))
 				.add(new EnableInput());
 		node.add(new SetPosition(beggar, cityBench));
 		
 	}
 		
 	//Macey + Jackson
 	 @BuilderMethod
 	 public void hint() {
 		var node = get(MyNodeLabels.hint.toString());
 		node.add(new Unpocket(player, scroll));
 		node.add(new Give(player, scroll, beggar));
 		node.add(new DialogSequence(beggar, player, List.of("Thank you for restoring my honor.")));
 		node.add(new NarrationSequence("Whenever I passed by the coin's case, I would hear a whispered voice")); 
 
 	}
 	
 	//Macey
 	@BuilderMethod
 	public void exit() {
 		var node = get(MyNodeLabels.exitCity.toString());
 		node.add(new HideDialog()).add(new DialogSequence(player, player, 
 				List.of("Where should I go now?"),
 				List.of("Ruins", "Forest", "Port", "City")));
 	}
 
 	//Macey
 		@BuilderMethod
 		public void ruins() {
 			var node = get(MyNodeLabels.toRuins.toString());
 			node.add(new DisableInput()).add(new HideDialog()).add(new Exit(player, exitWestEnd, true)).add(new Enter(player, ruinsDoor, true)).add(new EnableInput())
 				.add(new CreateCharacterSequence(oldMan))
 				.add(new SetPosition(oldMan, ruinsAltar))
 				.add(new FadeIn());
 			}
 		
 	//Macey
 		@BuilderMethod
 		public void ruinsRiddle() {
 			var node = get(MyNodeLabels.atRuins.toString());
 			node.add(new DialogSequence(player, oldMan,
 					List.of("Seeking guidance, traveler? Answer this riddle for direction, what is both the beginning and the end?"),
 					List.of("N", "S", "E", "W")));
 		}
 	
 	//Jackson
 		@BuilderMethod
 		public void wrongRiddleAnswer() {
 			var node = get(MyNodeLabels.wrongRiddleAnswer.toString());
 			node.add(new NarrationSequence("As soon as you made your choice, the ruins immediately collapsed, killing you instantly."));
 			node.add(new Wait(15)).add(new HideNarration());
 			node.add(new Die(player));
 		}
 		
 	//Jackson
 		@BuilderMethod
 		public void rightRiddleAnswer() {
 			var node = get(MyNodeLabels.rightRiddleAnswer.toString());
 			node.add(new HideDialog());
 			node.add(new NarrationSequence("Well done! Now head on over to the port."));
 			node.add(new Wait(15)).add(new HideNarration());
 		}
 	
 	//Jackson + Macey
 		@BuilderMethod
 		public void forest() {
 			var node = get(MyNodeLabels.toForest.toString());
 			node.add(new DisableInput()).add(new HideDialog()).add(new Exit(player, exitWestEnd, true)).add(new Enter(player, forestWestEnd, true)).add(new EnableInput())
 				.add(new Wait(5));
 			node.add(new DialogSequence(player, player,
 					List.of("You come to a fork in the forest path"),
 					List.of("Go left", "Go right")));
 		}
 	
 	//Macey
 		@BuilderMethod
 		public void forestLeft() {
 			var node = get(MyNodeLabels.leftForest.toString());
 			node.add(new DisableInput()).add(new HideDialog()).add(new Exit(player, forestEastEnd, true)).add(new Enter(player, campDoor, true)).add(new EnableInput());
 			node.add(new CreateCharacterSequence(knight))
 				.add(new SetPosition(knight, campHorse))
 				.add(new FadeIn());
 		}
 	
 	 //Macey
 	 	@BuilderMethod
 	 	public void knightChoice() {
 	 		var node = get(MyNodeLabels.fightk.toString());
 	 		node.add(new HideDialog());
 	 		node.add(new DialogSequence(player, knight,
 	 				List.of("Ye shall not pass!"),
 	 				List.of("Duel the Knight", "Retreat to the City")));
 	 		}
 		
 	 //Macey
 		@BuilderMethod
 		public void knightDuel() {
 			var node = get(MyNodeLabels.knightFight.toString());
 			node.add(new HideDialog());
 			node.add(new DialogSequence(player, knight,
 					List.of("How should I fight the knight?"),
 					List.of("Use my sword", "Use my compass", "Use my spellbook")));
 		}
 		
 	//Macey
 		@BuilderMethod
 		public void forestRight() {
 			var node = get(MyNodeLabels.rightForest.toString());
 			node.add(new DisableInput()).add(new HideDialog()).add(new Exit(player, forestEastEnd, true)).add(new Enter(player, spookyPathWest, true)).add(new EnableInput());
 			node.add(new CreateCharacterSequence(bandit))
 				.add(new SetPosition(bandit, spookyPathEastEnd))
 				.add(new FadeIn());
 		}
 	
 	 //Macey
 	 	@BuilderMethod
 	 	public void banditChoice() {
 	 		var node = get(MyNodeLabels.fightb.toString());
 	 		node.add(new HideDialog());
 	 		node.add(new DialogSequence(player, bandit,
 					List.of("Give me all of your loot!"),
 					List.of("Fight the bandit", "Retreat to the City")));
 	 		}
 		
 	//Macey
 		@BuilderMethod
 		public void banditDuel() {
 			var node = get(MyNodeLabels.banditFight.toString());
 			node.add(new HideDialog());
 			node.add(new DialogSequence(player, bandit,
 					List.of("How should I fight the bandit?"),
 					List.of("Use my sword", "Use my compass", "Use my spellbook")));
 		}
 	//Macey + Jackson
 		@BuilderMethod
 		public void duelWinK() {
 			var node = get(MyNodeLabels.winKnight.toString());
 			node.add(new Die(knight));
 			node.add(new DisableInput()).add(new HideDialog()).add(new Exit(player, campDoor, true)).add(new Enter(player, portDoor, true));
 		}
 		
 	//Macey + Jackson
 		@BuilderMethod
 		public void duelWinB() {
 			var node = get(MyNodeLabels.winBandit.toString());
 			node.add(new Die(bandit));
 			node.add(new DisableInput()).add(new HideDialog()).add(new Exit(player, spookyPathEast, true)).add(new Enter(player, portDoor, true));
 		}
 		
 	//Macey
 		@BuilderMethod
 		public void duelLose() {
 			var node = get(MyNodeLabels.loseD.toString());
 			node.add(new HideDialog());
 			node.add(new NarrationSequence("You died and darkness took over the kingdom!"));
 			node.add(new Wait(5))
 				.add(new HideNarration());
 			node.add(new Die(player));
 		}
 		
 	//Jackson
 		@BuilderMethod
 		public void death() {
 			var node = get(MyNodeLabels.death.toString());
 			node.add(new HideDialog());
 			node.add(new NarrationSequence("You died and darkness took over the kingdom!"));
 			node.add(new Wait(5))
				.add(new HideNarration());
 			node.add(new Die(player));
 	}
 	
 	//Macey
 		@BuilderMethod
 		public void port() {
 			var node = get(MyNodeLabels.toPort.toString());
 			node.add(new DisableInput()).add(new HideDialog()).add(new Exit(player, exitWestEnd, true)).add(new Enter(player, portDoor, true)).add(new EnableInput());
 			node.add(new CreateCharacterSequence(witch))
 				.add(new SetPosition(witch, portBarrel));
 	}
 		
 	 //Macey
 		@BuilderMethod
 		public void atPort() {
 			var node = get(MyNodeLabels.atPort.toString());
 			node.add(new DialogSequence(player, witch,
 					List.of("I know you seek the lost coin. Give me payment and I will offer you a safe trip across, or face the consequences!"),
 					List.of("Give up your tool", "Refuse")));
 	}
 		
 	//Macey
 		@BuilderMethod
 		public void portByself() {
 			var node = get(MyNodeLabels.portRefusal.toString());
 			node.add(new HideDialog());
 			node.add(new DialogSequence(player, player,
 					List.of("Should I try to swim across, or use a boat?"),
 					List.of("Swim", "Boat")));
 	}
 		
 	//Macey
 		@BuilderMethod
 		public void deathByWitch() {
 			var node = get(MyNodeLabels.witchDeath.toString());
 			node.add(new HideDialog());
 			node.add(new Attack(witch, player, true)).add(new Die(player));
 	}
 		
 	 //Macey
 		@BuilderMethod
 		public void cottage() {
 			var node = get(MyNodeLabels.cottage.toString());
 			node.add(new DisableInput()).add(new HideDialog()).add(new Exit(player, portDoor, true)).add(new Enter(player, farmExit, true)).add(new EnableInput());
 		}
 	
 	//Jackson
 		@BuilderMethod
 		public void atCottage() {
 			var node = get(MyNodeLabels.atCottage.toString());
 			node.add(new HideDialog());
 			node.add(new DialogSequence(player, player,
 					List.of("How should I try to enter the cottage?"),
 					List.of("Try the back door", "Try the front door")));
 		}
 		
 	//Macey
 		@BuilderMethod
 		public void backDoor() {
 			var node = get(MyNodeLabels.backDoor.toString());
 			node.add(new DisableInput()).add(new HideDialog()).add(new Exit(player, farmDoor, true)).add(new Enter(player, cottageDoor, true)).add(new EnableInput())
 				//.add(new CreateCharacterSequence(scalper))	
 				//.add(new SetPosition(scalper, cottageBar))	
 				.add(new SetPosition(scrollP, cottageBar))	
 				.add(new SetPosition(redPotion, cottageAlchemistTable))	
				.add(new SetPosition(greenPotion, cottageTable))	
				.add(new SetPosition(bluePotion, cottageBookshelf));	

 		}
 		
 	//Macey
 		@BuilderMethod
 		public void frontDoor() {
 			var node = get(MyNodeLabels.frontDoor.toString());
 			node.add(new DisableInput()).add(new HideDialog()).add(new Exit(player, farmDoor, true)).add(new Enter(player, cottageDoor, true)).add(new EnableInput())
 				//.add(new CreateCharacterSequence(scalper))	
				//.add(new SetPosition(scalper, cottageBackDoor))	
				.add(new SetPosition(scrollP , cottageBar))	
				.add(new SetPosition(redPotion, cottageAlchemistTable))	
				.add(new SetPosition(greenPotion, cottageTable))	
				.add(new SetPosition(bluePotion, cottageBookshelf));
 		}	
 		
 	 //Macey
 		@BuilderMethod
 		public void potionScroll() {
 			var node = get(MyNodeLabels.inCottage.toString());
 			node.add(new NarrationSequence("The Red one whispers. The Blue one blows wind. The Green one sparkles in the sun."));
 			node.add(new Wait(10))
 				.add(new HideNarration());
 		}
 		
 	 //Macey
 		@BuilderMethod
 		public void correctPotionRed() {
 			var node = get(MyNodeLabels.correctPotion.toString());
 			node.add(new HideDialog());
 			node.add(new Pickup(player, redPotion));
 			node.add(new Drink(player));
 			node.add(new Pocket(player, redPotion));	
 		}
 		
 	//Macey + Jackson
 		@BuilderMethod
 		public void wrongPotionBlue() {
 			var node = get(MyNodeLabels.wrongPotion1.toString());
 			node.add(new HideDialog());
 			node.add(new Pickup(player, bluePotion));
 			node.add(new Drink(player));
 			node.add(new NarrationSequence("You drank the potion. Unfortunately, it was poison. You died!"));
 			node.add(new Die(player));
 		}
 		
 	 	//Macey + Jackson
 		@BuilderMethod
 		public void wrongPotionGreen() {
 			var node = get(MyNodeLabels.wrongPotion2.toString());
 			node.add(new HideDialog());
 			node.add(new Pickup(player, greenPotion));
 			node.add(new Drink(player));
 			node.add(new NarrationSequence("You drank the potion. Unfortunately, it was poison. You died!"));
 			node.add(new Die(player));
 		}
 		
 	 //Macey
 		@BuilderMethod
 		public void inDungeon() {
 			var node = get(MyNodeLabels.inDungeon.toString());
 			
 			node.add(new DisableInput()).add(new HideDialog()).add(new Exit(player, cottageDoor, true)).add(new Enter(player, dungeonDoor, true)).add(new EnableInput());
 			node.add(new SetPosition(coin, dungeonTable));
 		}
 		
 	//Jackson
 		@BuilderMethod
 		public void findCoin() {
 			var node = get(MyNodeLabels.findCoin.toString());
 			node.add(new Pickup(player, coin))
 				.add(new Pocket(player, coin));
 		}
 		
 	//Jackson
 		@BuilderMethod
 		public void returnKingdom() {
 			var node = get(MyNodeLabels.returnKingdom.toString());
 			node.add(new DisableInput()).add(new HideDialog()).add(new Exit(player, dungeonDoor, true)).add(new Enter(player, greatHallDoor, true)).add(new EnableInput());
 			node.add(new SetPosition(merlin, greatHallThrone));
 		}
 		
 	//Macey + Jackson
 		@BuilderMethod
 		public void merlinFinalSpeech() {
 			var node = get(MyNodeLabels.finalScene.toString());
 			node.add(new Unpocket(player, coin));
 			node.add(new Give(player, coin, merlin));
 			node.add(new DialogSequence(player, player,
 					List.of("I praise your bravery young hero, and for such, I hereby elect you the head of the Camelot knights!"),
 					List.of("It is an honor!")));
 			node.add(new HideDialog());
 			node.add(new Kneel(player));
 		}
 		
 	
 		
 }


