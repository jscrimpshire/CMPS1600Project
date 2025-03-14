package myclassproject.mystorygraph;

import static myclassproject.mystorygraph.MyStoryEntities.*;
import static myclassproject.questexample.QuestStoryEntities.bandit;
import static myclassproject.questexample.QuestStoryEntities.cityDoor;
import static myclassproject.questexample.QuestStoryEntities.cottageDoor;
import static myclassproject.questexample.QuestStoryEntities.player;
import static myclassproject.questexample.QuestStoryEntities.sword;

import java.util.List;

import com.storygraph.*;
import com.actions.*;
import com.actions.utility.*;
import com.sequences.*;

import myclassproject.mystorygraph.MyChoiceLabels;
import myclassproject.questexample.NodeLabels;

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
		
		var root = get(MyChoiceLabels.root.toString());
		root.add(new CreateAll(List.of(greatHall)))
		.add(new CreateCharacterSequence(player))
		.add(new SetPosition(player, greatHall))
		.add(new CreateCharacterSequence(merlin))
		.add(new SetPosition(merlin, greatHall))
		.add(new SetCameraFocus(player))
		.add(new ShowMenu())
		.add(new HideMenu())
		.add(new EnableInput());
	
		
	}
	
	//Macey
	@BuilderMethod
	public void startingTool() {
		var node = get(MyChoiceLabels.merlinToolSpeech.toString());
		node.add(new DialogSequence(player, merlin, 
				List.of("The Coin of Camelot is in peril. Choose your starting tool for the journey."),
				List.of("SpellBook", "Sword", "Compass")));

		node.add(new DisableInput()).add(new Exit(player, greatHallDoor, true)).add(new Enter(player, cityDoor, true))
				.add(new EnableInput());
	}
	
	//Macey
	@BuilderMethod
	public void atGreatHallLocation() {
		var node = get(NodeLabels.atGreatHall.toString());
		node.add(new HideMenu()).add(new EnableInput());
	}
	
	//Macey
	@BuilderMethod
	public void spellbook() {
		var node = get(MyChoiceLabels.sbItem.toString());
		node.add(new Take(player, spellBook, merlin))
			.add(new Pocket(player, spellBook));
	}
	
	//Macey
	@BuilderMethod
	public void sword() {
		var node = get(MyChoiceLabels.sItem.toString());
		node.add(new Take(player, sword, merlin))
			.add(new Pocket(player, sword));
	}
	
	//Macey
	@BuilderMethod
	public void compass() {
		var node = get(MyChoiceLabels.compItem.toString());
		node.add(new Take(player, compass, merlin))
			.add(new Pocket(player, compass));
	}
	
	//Macey
	@BuilderMethod
	public void specialQuest() {
		var node = get(MyChoiceLabels.beggarQuest.toString());
		node.add(new CreateCharacterSequence(beggar))
			.add(new SetPosition(beggar, city));
		node.add(new DialogSequence(player, beggar, 
				List.of("Please help me."),
				List.of("Yes", "No")));
	}
	
	//Macey
	@BuilderMethod
	public void ignore() {
		var node = get(MyChoiceLabels.refuse.toString());
		node.add(new HideDialog());
	}
	
	//Macey
	@BuilderMethod
	public void listen() {
		var node = get(MyChoiceLabels.accept.toString());
		node.add(new HideDialog()).add(new DialogSequence(player, beggar, 
				List.of("I used to be a guard in the Kingdom. After the disappearence of the coin, I was let go. I lost my home and have been living on the streets since. Inside the castle, I hid a scroll. Somewhere in the library, it lies. It would prove my innocence. Please retreive it so I can get back on my feet"), 
				List.of("Ok", "No")));
	}
	
	//Macey
	@BuilderMethod
	public void questCont() {
		var node = get(MyChoiceLabels.goToLibrary.toString());
		node.add(new DisableInput()).add(new Exit(player, cityDoor, true)).add(new Enter(player, libraryDoor, true))
				.add(new EnableInput());
	}
	
	//Macey
	@BuilderMethod
	public void spellbookFind() {
		var node = get(MyChoiceLabels.findScrollSB.toString());
		node.add(new NarrationSequence("You used the spellbook to find the scroll.")).add(new Pickup(player, scroll, Bookcase)).add(new Pocket(player, scroll));
	}
	
	//Macey
	@BuilderMethod
	public void compassFind() {
		var node = get(MyChoiceLabels.findScrollComp.toString());
		node.add(new NarrationSequence("You used the compass to find the scroll.")).add(new Pickup(player, scroll, Bookcase)).add(new Pocket(player, scroll));
	}
	
	//Macey
	@BuilderMethod
	public void regFind() {
		var node = get(MyChoiceLabels.findScrollReg.toString());
		node.add(new NarrationSequence("Without a tool, you decide to look by hand. Eventually you see it within the shelves.")).add(new Pickup(player, scroll, Bookcase));
	}
	
	//Macey
	@BuilderMethod
	public void hint() {
		var node = get(MyChoiceLabels.leaveLibrary.toString());
		node.add(new DisableInput()).add(new Exit(player, libraryDoor, true)).add(new Enter(player, cityDoor, true))
				.add(new EnableInput());
		node.add(new Give(player, scroll, beggar))
		.add(new Unpocket(player, scroll));
	}
	
	//Macey
	@BuilderMethod
	public void exit() {
		var node = get(MyChoiceLabels.exitCity.toString());
		node.add(new DialogSequence(player, player, 
				List.of("Where should I go now?"),
				List.of("Ruins", "Forest", "Port", "City")));

	}
	
	//Jackson
	@BuilderMethod
	public void forest() {
		var node = get(MyChoiceLabels.forest.toString());
		node.add(new DialogSequence(player, player,
				List.of("You come to a fork in the forest path"),
				List.of("Go left", "Go right")));
	}
	
	//Jackson
	@BuilderMethod
	public void death() {
		var node = get(MyChoiceLabels.death.toString());
		node.add(new NarrationSequence("You died and darkness took over the kingdom!"));
	}
}
