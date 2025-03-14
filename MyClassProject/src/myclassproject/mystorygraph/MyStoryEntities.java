package myclassproject.mystorygraph;

import com.entities.Characters;
import com.entities.Item;
import com.entities.Place;
import com.enums.BodyTypes;
import com.enums.Clothing;
import com.enums.Colors;
import com.enums.HairStyle;
import com.enums.ItemTypes;
import com.enums.PlaceTypes;

public final class MyStoryEntities {
	//Create an instance of Character, Place, Furniture, and Item classes 
	//for each of the characters, places, furniture, and items in your story
	//Make that instance public static final
	//e.g. public static final Characters player = new Characters("Player", BodyTypes.D, Clothing.Peasant, HairStyle.Short, Colors.Black, 6);
	//You can access these instances in your EdgeBuilder and NodeBuilder classes by importing:
	//import static myclassproject.mystorygraph.MyStoryEntities.*;
	
	
	//Characters
	public static final Characters player = new Characters("Player", BodyTypes.D, Clothing.HeavyArmour, HairStyle.Long, Colors.Black, 6);
	public static final Characters merlin = new Characters("Merlin", BodyTypes.H, Clothing.Warlock, HairStyle.Mage_Full, Colors.Gray, 6);
	public static final Characters beggar = new Characters("Beggar", BodyTypes.B, Clothing.Beggar, HairStyle.Spiky, Colors.Blonde, 6);
	
	public static final Characters witch = new Characters("Witch", BodyTypes.G, Clothing.Beggar, HairStyle.Long, Colors.Black, 6);
	
	//Places
	public static final Place greatHall = new Place("GreatHall", PlaceTypes.GreatHall);
	public static final Place city = new Place("City", PlaceTypes.City);
	public static final Place library = new Place("Library", PlaceTypes.Library);
	
	public static final Place cottage = new Place("Home", PlaceTypes.Cottage);
	
	//Items
	public static final Item sword = new Item("Sword", ItemTypes.Sword);
	public static final Item spellBook = new Item("SpellBook", ItemTypes.SpellBook);
	public static final Item compass = new Item("Compass", ItemTypes.Compass);
	public static final Item scroll = new Item("Scroll", ItemTypes.Scroll);
	
}
