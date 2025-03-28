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
		root.add(new CreateAll(List.of(greatHall)))
		.add(new CreateCharacterSequence(player))
		.add(new SetPosition(player, greatHall))
		.add(new CreateCharacterSequence(merlin))
		.add(new SetPosition(merlin, greatHall))
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
		node.add(new Take(player, spellBook, merlin))
			.add(new Pocket(player, spellBook));
	}
	
	//Macey
	@BuilderMethod
	public void sword() {
		var node = get(MyNodeLabels.swordTool.toString());
		node.add(new Take(player, sword, merlin))
			.add(new Pocket(player, sword));
	}
	
	//Macey
	@BuilderMethod
	public void compass() {
		var node = get(MyNodeLabels.compassTool.toString());
		node.add(new Take(player, compass, merlin))
			.add(new Pocket(player, compass));
	}
	
	//Macey
	@BuilderMethod
	public void specialQuest() {
		var node = get(MyNodeLabels.beggarQuest.toString());
		node.add(new DisableInput()).add(new Exit(player, greatHallDoor, true)).add(new Enter(player, cityDoor, true));
		node.add(new CreateCharacterSequence(beggar))
			.add(new SetPosition(beggar, city));
		node.add(new SetPosition(player, city));
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
		node.add(new DisableInput()).add(new Exit(player, cityDoor, true)).add(new Enter(player, libraryDoor, true))
		.add(new SetPosition(player, library))	
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
	public void hint() {
		var node = get(MyNodeLabels.leaveLibrary.toString());
		node.add(new DisableInput()).add(new HideDialog()).add(new Exit(player, libraryDoor, true)).add(new Enter(player, cityDoor, true))
				.add(new EnableInput());
		node.add(new SetPosition(beggar, city));
		node.add(new SetPosition(player, city));
		node.add(new Give(player, scroll, beggar))
		.add(new Unpocket(player, scroll));
		node.add(new NarrationSequence("Whenever I passed by the coin's case, I would her a whispered voice")); 

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
			node.add(new SetPosition(player, ruins))
				.add(new SetPosition(oldMan, ruins.ruinsAltar));
			node.add(new DialogSequence(player, oldMan,
					List.of("Seeking guidance, traveler? Answer this riddle for direction, what is both the beginning and the end?"),
					List.of("N", "S", "E", "W")));
			}
	
	//Jackson
		@BuilderMethod
		public void wrongRiddleAnswer() {
			var node = get(MyNodeLabels.wrongRiddleAnswer());
			node.add(new NarrationSequence("As soon as you made your choice, the ruins immediately collapsed, killing you instantly."));
			node.add(new Die(player));
		}
		
	//Jackson
		@BuilderMethod
		public void rightRiddleAnswer() {
			var node = get(MyNodeLabels.rightRiddleAnswer());
			node.add(new NarrationSequence("You place the compass on the platform, and it begins to spin. After a moment, the compass begins to set directions to a cottage across the port."));
		}
	
	//Jackson + Macey
		@BuilderMethod
		public void forest() {
			var node = get(MyNodeLabels.toForest.toString());
			node.add(new SetPosition(player, forest));
			node.add(new DialogSequence(player, player,
					List.of("You come to a fork in the forest path"),
					List.of("Go left", "Go right")));
		}
	
	//Macey
		@BuilderMethod
		public void forestLeft() {
			var node = get(MyNodeLabels.leftForest.toString());
			node.add(new SetPosition(player, camp))
				.add(new SetPosition(knight, camp.Horse));
			node.add(new DialogSequence(player, knight,
					List.of("Ye shall not pass!"),
					List.of("Duel the knight", "Retreat to the City")));
		}
		
	//Macey
		@BuilderMethod
		public void forestRigth() {
			var node = get(MyNodeLabels.rightForest.toString());
			node.add(new SetPosition(player, spookyPath))
				.add(new SetPosition(bandit, spookyPath.EastEnd));
			node.add(new DialogSequence(player, bandit,
					List.of("Give me all of your loot!"),
					List.of("Fight the bandit", "Retreat to the City")));
		}

	//Macey
		@BuilderMethod
		public void knightDuel() {
			var node = get(MyNodeLabels.knightFight.toString());
			node.add(new DialogSequence(player, bandit,
					List.of("How should I fight the knight?"),
					List.of("Use my sword", "Use my compass", "Use my spellbook")));
		}
	
	//Macey
		@BuilderMethod
		public void banditDuel() {
			var node = get(MyNodeLabels.banditFight.toString());
			node.add(new DialogSequence(player, bandit,
					List.of("How should I fight the bandit?"),
					List.of("Use my sword", "Use my compass", "Use my spellbook")));
		}
	//Macey
		@BuilderMethod
		public void duelWin() {
			var node = get(MyNodeLabels.winD.toString());
			node.add(new Die(bandit));
			node.add(new Die(knight));
		}
		
	//Macey
		@BuilderMethod
		public void duelLose() {
			var node = get(MyNodeLabels.loseD.toString());
			node.add(new NarrationSequence("You died and darkness took over the kingdom!"));
			node.add(new Die(player));
		}
		
	//Jackson
		@BuilderMethod
		public void death() {
				var node = get(MyNodeLabels.death.toString());
			node.add(new NarrationSequence("You died and darkness took over the kingdom!"));
			node.add(new Die(player));
	}
	
	//Macey
		@BuilderMethod
		public void port() {
			var node = get(MyNodeLabels.toPort.toString());
			node.add(new SetPosition(player, port))
				.add(new SetPosition(witch, port));
			node.add(new DialogSequence(player, witch,
					List.of("I know you seek the lost coin. Give me payment and I will offer you a safe trip across, or face the consequences!"),
					List.of("Give up your tool", "Refuse")));
	}
		
	//Macey
		@BuilderMethod
		public void portByself() {
			var node = get(MyNodeLabels.atPort.toString());
			node.add(new DialogSequence(player, player,
					List.of("Should I try to swim across, or use a boat?"),
					List.of("Swim", "Boat")));
	}
		
	//Macey
		@BuilderMethod
		public void deathByWitch() {
			var node = get(MyNodeLabels.deathByWitch.toString());
			node.add(new Attack(witch, player, true)).add(new Die(player));
	}
	
	//Jackson
		@BuilderMethod
		public void cottage() {
			var node = get(MyNodeLabels.cottage.toString());
			node.add(new DialogSequence(player, player,
					List.of("How should I try to enter the cottage?"),
					List.of("Try the window", "Try the door")));
		}
		
	//Jackson
		@BuilderMethod
		public void window() {
			var node = get(MyNodeLabels.window.toString());
			node.add(new DialogSequence(player, player,
					List.of("You slide the window open carefully, and sneak into the cottege. Inside, you find three doors. From the first door you hear a whimsical tune, the second emits a whispered chant, and from the third you hear loud snoring."),
					List.of("Try door one", "Try door two", "Try door three")));
		}
	
	//Jackson
		@BuilderMethod
		public void spellDeath() {
			var node = get(MyNodeLabels.spellDeath.toString());
			node.add(new NarrationSequence("You open the first door and find a swirling wind. You know it well. It is a magical spell. It engulfs you and you are consumed by darkness. You died!"));
			node.add(new Die(player));
		}
	
	//Jackson
		@BuilderMethod
		public void guardDeath() {
			var node = get(MyNodeLabels.guardDeath.toString());
			node.add(new NarrationSequence("You open the third door and find the sleeping gaurd. He stirs at the sound of the door and beats you to death. You died!"));
			node.add(new Die(player));
		}
		
	//Jackson
		@BuilderMethod
		public void findCoin() {
			var node = get(MyNodeLabels.findCoin.toString());
			node.add(new NarrationSequence("You open the second door to find a cloth on the ground shadowed by darkness. You remove the cloth to find a circular gold disk. As you bring it to the light, you realize it is the lost coin of Camelot! Sneaking back out the window, you make your way back to the kingdom."))
		}
		
	//Jackson
		@BuilderMethod
		public void returnKingdom() {
			var node = get(MyNodeLabels.returnKingdom.toString());
			node.add(new NarrationSequence("You return to the kingdom, and already it feels brighter. Finding Merlin, you show him the coin. He praises your bravery, and offers you a reward by electing you the head of the Camelot knights."));
		}
		
	
		
}


