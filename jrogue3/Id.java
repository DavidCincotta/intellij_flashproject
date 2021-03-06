import java.util.StringTokenizer;
import java.io.*;
import java.awt.Event;

class Id implements Serializable{
	private static final long serialVersionUID= 3L;
	int value;
	String title;
	String real;
	int id_status;

	static int xtab[]= { 1, 1, 0,-1,-1,-1, 0, 1, 0};
	static int ytab[]= { 0, 1, 1, 1, 0,-1,-1,-1, 0};

	static String potionslist[]= {
	"100", "blue ",		"of increase strength ",
	"250", "red ",		"of restore strength ",
	"100", "green ",	"of healing ",
	"200", "grey ",		"of extra healing ",
	 "10", "brown ",	"of poison ",
	"300", "clear ",	"of raise level ",
	 "10", "pink ",		"of blindness ",
	 "25", "white ",	"of hallucination ",
	"100", "purple ",	"of detect monster ",
	"100", "black ",	"of detect things ",
	 "10", "yellow ",	"of confusion ",
	 "80", "plaid ",	"of levitation ",
	"150", "burgundy ",	"of haste self ",
	"145", "beige ",	"of see invisible "};
	static Id id_potions[]= idlist(potionslist, 0);

	//paralysis,poison,thirst
	static void mix_colors(Randomx rand){
		for(int j= id_potions.length; --j>1;){
			int k= rand.get(j-1);
			String t= id_potions[j].title;
			id_potions[j].title= id_potions[k].title;
			id_potions[k].title= t;
		}
	}
	static String scrollslist[]= {
	"505", "", "of protect armor ",
	"200", "", "of hold monster ",
	"235", "", "of enchant weapon ",
	"235", "", "of enchant armor ",
	"175", "", "of identify ",
	"190", "", "of teleportation ",
	 "25", "", "of sleep ",
	"610", "", "of scare monster ",
	"210", "", "of remove curse ",
	 "80", "", "of create monster ",
	 "25", "", "of aggravate monster ",
	"180", "", "of magic mapping ",
	 "90", "", "of confuse monster ",
	  "5", "", "of blank paper ",		// only with old
	 "50", "", "of light "};			// only with old
//gold,food
	static String syllables[]= {
		"blech ","foo ","barf ","rech ","bar ",
		"blech ","quo ","bloto ","oh ","caca ",
		"blorp ","erp ","festr ","rot ","slie ",
		"snorf ","iky ","yuky ","ooze ","ah ",
		"bahl ","zep ","druhl ","flem ","behil ",
		"arek ","mep ","zihr ","grit ","kona ",
		"kini ","ichi ","tims ","ogr ","oo ",
		"ighr ","coph ","swerr ","mihln ","poxi "
	};
	static Id id_scrolls[]= idlist(scrollslist, 0);

	static String weaponslist[]= {
	"150", "short bow ", "",
	  "8", "darts ", "",
	 "15", "arrows ", "",
	 "27", "daggers ", "",
	 "35", "shurikens ", "",
	"360", "mace ", "",
	"470", "long sword ", "",
	"580", "two-handed sword ", ""};
	static Id id_weapons[]= idlist(weaponslist, 0);

	static String armorslist[]= {
	"30", "leather armor ", "",
	"30", "ring mail ", "",
	"40", "scale mail ", "",
	"50", "chain mail ", "",
	"60", "banded mail ", "",
	"60", "splint mail ", "",
	"70", "plate mail ", ""};
	static Id id_armors[]= idlist(armorslist, 0);

	static String wandslist[]= {
	 "25", "", "of teleport away ",
	 "50", "", "of slow monster ",
	  "8", "", "of invisibility ",
	 "55", "", "of polymorph ",
	  "2", "", "of haste monster ",
	 "20", "", "of magic missile ",
	 "20", "", "of cancellation ",
	  "0", "", "of do nothing ",
	 "35", "", "of drain life ",
	 "20", "", "of cold ",
	 "20", "", "of fire ",
	 "15", "", "of light "};			// only with old 
	static Id id_wands[]= idlist(wandslist, 0);
// lightning,teleport to,striking

	static String ringslist[]= {
	 "250", "", "of stealth ",
	 "100", "", "of teleportation ",
	 "255", "", "of regeneration ",
	 "295", "", "of slow digestion ",
	 "200", "", "of add strength ",
	 "250", "", "of sustain strength ",
	 "250", "", "of dexterity ",
	  "25", "", "of adornment ",
	 "300", "", "of see invisible ",
	 "290", "", "of maintain armor ",
	 "270", "", "of searching "};
	static Id id_rings[]= idlist(ringslist, 0);
//aggravate,damage,protection

	static String wand_materials[]= {
		"steel ","bronze ","gold ","silver ","copper ",
		"nickel ","cobalt ","tin ","iron ","magnesium ",
		"chrome ","carbon ","platinum ","silicon ","titanium ",

		"teak ","oak ","cherry ","birch ","pine ",
		"cedar ","redwood ","balsa ","ivory ","walnut ",
		"maple ","mahogany ","elm ","palm ","wooden "
	};
	static boolean is_wood[]= new boolean[wand_materials.length];
	static {
		boolean wood= false;
		for(int k= 0; k<is_wood.length; k++){
			if(wand_materials[k].compareTo("teak")==0)
				wood= true;
			is_wood[k]= wood;
		}
	}
	static String gems[]= {
		"diamond ","stibotantalite ","lapi-lazuli ","ruby ","emerald ",
		"sapphire ","amethyst ","quartz ","tiger-eye ","opal ",
		"agate ","turquoise ","pearl ","garnet "
	};
	static void make_scroll_titles(Randomx rand){
		// Also name the wands and rings
		for(int i= 0; i < id_scrolls.length; i++){
			int sylls= rand.get(2, 5);
			String ti= "'";
			for(int j= 0; j < sylls; j++){
				int s= rand.get(1, syllables.length-1);
				ti= ti.concat(syllables[s]);
			}
			id_scrolls[i].title= ti.concat("' ");
		}
		int perm[]= rand.permute(wand_materials.length);
		for(int i= 0; i<id_wands.length; i++)
			id_wands[i].title= wand_materials[perm[i]];

		perm= rand.permute(gems.length);
		for(int i= 0; i<id_rings.length; i++)
			id_rings[i].title= gems[perm[i]];
	}
	static final int ARMOR=			0x00100;
	static final int WEAPON=		0x00200;
	static final int SCROLL=		0x00400;
	static final int POTION=		0x00800;
	static final int GOLD=			0x01000;
	static final int FOOD=			0x02000;
	static final int WAND=			0x04000;
	static final int RING=			0x08000;
	static final int AMULET=		0x10000;
	static final int ALL_TOYS=		0x1ff00;

	static final int LEATHER=			0+ARMOR;
	static final int RINGMAIL=			1+ARMOR;
	static final int SCALE=				2+ARMOR;
	static final int CHAIN=				3+ARMOR;
	static final int BANDED=			4+ARMOR;
	static final int SPLINT=			5+ARMOR;
	static final int PLATE=				6+ARMOR;
	static final int ARMORS=			7+ARMOR;

	static final int BOW=				0+WEAPON;
	static final int DART=				1+WEAPON;
	static final int ARROW=				2+WEAPON;
	static final int DAGGER=			3+WEAPON;
	static final int SHURIKEN=			4+WEAPON;
	static final int MACE=				5+WEAPON;
	static final int LONG_SWORD=		6+WEAPON;
	static final int TWO_HANDED_SWORD=	7+WEAPON;
	static final int WEAPONS=	8;

	static final int PROTECT_ARMOR=		0+SCROLL;
	static final int HOLD_MONSTER=		1+SCROLL;
	static final int ENCH_WEAPON=		2+SCROLL;
	static final int ENCH_ARMOR=		3+SCROLL;
	static final int IDENTIFY=			4+SCROLL;
	static final int TELEPORT=			5+SCROLL;
	static final int SLEEP=				6+SCROLL;
	static final int SCARE_MONSTER=		7+SCROLL;
	static final int REMOVE_CURSE=		8+SCROLL;
	static final int CREATE_MONSTER=	9+SCROLL;
	static final int AGGRAVATE_MONSTER=	10+SCROLL;
	static final int MAGIC_MAPPING=		11+SCROLL;
	static final int CON_MON=			12+SCROLL;
	static final int BLANK_PAPER=		13+SCROLL;
	static final int LIGHT=				14+SCROLL;
	static final int SCROLS=			15+SCROLL;

	static final int INCREASE_STRENGTH=	0+POTION;
	static final int RESTORE_STRENGTH=	1+POTION;
	static final int HEALING=			2+POTION;
	static final int EXTRA_HEALING=		3+POTION;
	static final int POISON=			4+POTION;
	static final int RAISE_LEVEL=		5+POTION;
	static final int BLINDNESS=			6+POTION;
	static final int HALLUCINATION=		7+POTION;
	static final int DETECT_MONSTER=	8+POTION;
	static final int DETECT_TOYS=		9+POTION;
	static final int CONFUSION=			10+POTION;
	static final int LEVITATION=		11+POTION;
	static final int HASTE_SELF=		12+POTION;
	static final int SEE_INVISIBLE=		13+POTION;
	static final int POTIONS=			14+POTION;

	static final int TELE_AWAY=			0+WAND;
	static final int SLOW_MONSTER=		1+WAND;
	static final int INVISIBILITY=		2+WAND;
	static final int POLYMORPH=			3+WAND;
	static final int HASTE_MONSTER=		4+WAND;
	static final int MAGIC_MISSILE=		5+WAND;
	static final int CANCELLATION=		6+WAND;
	static final int DO_NOTHING=		7+WAND;
	static final int DRAIN_LIFE=		8+WAND;
	static final int COLD=				9+WAND;
	static final int FIRE=				10+WAND;
	static final int LIGHTSTICK=		11+WAND;
	static final int WANDS=				12+WAND;

	static final int STEALTH=			0+RING;
	static final int R_TELEPORT=		1+RING;
	static final int REGENERATION=		2+RING;
	static final int SLOW_DIGEST=		3+RING;
	static final int ADD_STRENGTH=		4+RING;
	static final int SUSTAIN_STRENGTH=	5+RING;
	static final int DEXTERITY=			6+RING;
	static final int ADORNMENT=			7+RING;
	static final int R_SEE_INVISIBLE=	8+RING;
	static final int MAINTAIN_ARMOR=	9+RING;
	static final int SEARCHING=			10+RING;
	static final int RINGS=				11+RING;

	static final int RATION=			0+FOOD;
	static final int FRUIT=				1+FOOD;

	static final int NOT_USED		=0;
	static final int BEING_WIELDED	=01;
	static final int BEING_WORN		=02;
	static final int ON_LEFT_HAND	=04;
	static final int ON_RIGHT_HAND	=010;
	static final int ON_EITHER_HAND	=014;
	static final int BEING_USED		=017;

	static final int UNIDENTIFIED=	0;
	static final int IDENTIFIED= 1;
	static final int CALLED= 2;

	static final int UPWARD =0;
	static final int UPRIGHT =1;
	static final int RIGHT =2;
	static final int DOWNRIGHT =3;
	static final int DOWN =4;
	static final int DOWNLEFT =5;
	static final int LEFT =6;
	static final int UPLEFT =7;
	static final int DIRS =8;

	static int is_direction(int c){
		switch(c){
		case Event.LEFT:
		case 'h':	return LEFT;
		case Event.DOWN:
		case 'j':	return DOWN;
		case Event.UP:
		case 'k':	return UPWARD;
		case Event.RIGHT:
		case 'l':	return RIGHT;
		case Event.END:
		case 'b':	return DOWNLEFT;
		case Event.HOME:
		case 'y':	return UPLEFT;
		case Event.PGUP:
		case 'u':	return UPRIGHT;
		case Event.PGDN:
		case 'n':	return DOWNRIGHT;
		case '\033':return -1;
		}
		return -2;
	}
	static int get_dir(int srow, int scol, int drow, int dcol){
		if(srow > drow)
			return scol>dcol? UPLEFT : (scol<dcol? UPRIGHT : UPWARD);
		if(srow < drow)
			return scol>dcol? DOWNLEFT : (scol<dcol? DOWNRIGHT : DOWN);
		return scol<dcol? RIGHT : LEFT;
	}
	static Id idlist(String list[], int status)[]{
		int n= list.length/3;
		int i= 0;
		Id ids[]= new Id[n];
		for(int k= 0; k<n; k++){
			ids[k]= new Id();
			ids[k].value= Integer.parseInt(list[i++]);
			ids[k].title= list[i++];
			ids[k].real= list[i++];
			ids[k].id_status= status;
		}
		return ids;
	}
	static void list_items(){
		id_potions= idlist(potionslist, 0);
		id_scrolls= idlist(scrollslist, 0);
		id_weapons= idlist(weaponslist, 0);
		id_armors= idlist(armorslist, 0);
		id_wands= idlist(wandslist, 0);
		id_rings= idlist(ringslist, 0);
	}
	static boolean is_vowel(int ch){
		if(ch < 'a')ch += 32;
		return  ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u';
	}
	static char get_mask_char(int mask){
		switch(mask&ALL_TOYS){
		case SCROLL:	return '?';
		case POTION:	return '!';
		case GOLD:		return '*';
		case FOOD:		return ':';
		case WAND:		return '/';
		case ARMOR:		return ']';
		case WEAPON:	return ')';
		case RING:		return '=';
		case AMULET:	return ',';
		default:		return '~';	/* unknown, something is wrong */
		}
	}
	static Id get_id_table(int kind)[]{
		switch(kind&ALL_TOYS){
		case SCROLL:		return id_scrolls;
		case POTION:	return id_potions;
		case WAND:		return id_wands;
		case RING:		return id_rings;
		case WEAPON:	return id_weapons;
		case ARMOR:		return id_armors;
		}
		return null;
	}
	static Id get_id_table(Toy obj)[]{
		return get_id_table(obj.kind);
	}
	static int get_value(int kind, boolean identified){
		Id id_table[]= get_id_table(kind);
		int v= 0;

		if(id_table!=null){
			v= id_table[kind & 255].value;
			if(!identified)
				v= v/2;
		}else switch(kind&ALL_TOYS){
		case AMULET:v= 1000;
		case GOLD:	v= 1;
		case FOOD:	v= 2;
		default:	break;
		}
		return v;
	}
	static String get_desc(Toy obj){
		String item_name;
		String desc= "";
		int i;
		int itstatus= 99;
		int species= obj.kind & ALL_TOYS;
		int what= obj.kind & 255;

		if(species == AMULET)
			return "the amulet of Yendor ";

		if(species == GOLD)
			return "" + obj.quantity + " pieces of gold";

		item_name= obj.name();

		if(species != ARMOR)
			desc= obj.quantity==1? "a " : ""+obj.quantity+" ";

		if(species==FOOD){
			if(obj.kind == RATION)
				desc= obj.quantity>1 ? "" + obj.quantity + " rations of " : "some ";
			else
				desc= "a ";
			desc= desc + item_name;
			itstatus= 98;	/* Flag just name it */
		}
		Id id_table[]= get_id_table(obj);

		if(0 != (species & (WEAPON | ARMOR | WAND | RING)))
			itstatus= UNIDENTIFIED;
		if(itstatus==99)
			itstatus= id_table[what].id_status;
		while(itstatus!=98)switch(itstatus){
		case UNIDENTIFIED:
			switch(species){
			case SCROLL:
				desc= desc + item_name + "entitled: " + id_table[what].title;
				break;
			case POTION:
				desc= desc + id_table[what].title + item_name;
				break;
			case WAND:
			case RING:
				if(obj.identified || id_table[what].id_status == IDENTIFIED){
					itstatus= IDENTIFIED;
				}else if(id_table[what].id_status == CALLED)
					itstatus= CALLED;
				else
					desc= desc + id_table[what].title + item_name;
				break;
			case ARMOR:
				if(obj.identified)
					itstatus= IDENTIFIED;
				else
					desc= desc + id_table[what].title;
				break;
			case WEAPON:
				if(obj.identified)
					itstatus= IDENTIFIED;
				else
	 				desc= desc + obj.name();
				break;
			}
			if(itstatus==UNIDENTIFIED)
				itstatus= 98;
			break;
		case CALLED:
			switch(species){
			case SCROLL:
			case POTION:
			case WAND:
			case RING:
				desc= desc + item_name + "called " + id_table[what].title;
				break;
			}
			itstatus= 98;
			break;
		case IDENTIFIED:
			switch(species){
			case SCROLL:
			case POTION:
				desc= desc + item_name + id_table[what].real;
				break;
			case RING:
				if(obj.identified){
					if(obj.kind==DEXTERITY || obj.kind==ADD_STRENGTH){
						if(obj.klass>0)
							desc= desc + '+';
						desc= desc + obj.klass;
					}
				}
				desc= desc + item_name + id_table[what].real;
				break;
			case WAND:
				desc= desc + item_name + id_table[what].real;
				if(obj.identified)
					desc= desc + '[' + obj.klass + ']';
				break;
			case ARMOR:
				if(obj.d_enchant>=0)
					desc= desc + '+';
				desc= desc + obj.d_enchant + " " + id_table[what].title
					+ '[' + obj.get_armor_class() + ']';
				break;
			case WEAPON:
				if(obj.hit_enchant>=0)
						desc= desc + '+';
				desc= desc + obj.hit_enchant + ',';
				if(obj.d_enchant>=0)
						desc= desc + '+';
				desc= desc + obj.d_enchant + " " + obj.name();
				break;
			}
			itstatus= 98;
			break;
		}
		if(desc.length() > 3)
		if(desc.charAt(0)=='a' && desc.charAt(1)==' '){
			if(is_vowel(desc.charAt(2))){
				String t= desc.substring(1);
				desc= "an" + t;
			}
		}
		if(0 != (obj.in_use_flags & BEING_WIELDED))
			desc= desc  + " in hand";
		else if(0 != (obj.in_use_flags & BEING_WORN))
			desc= desc  + " being worn";
		else if(0 != (obj.in_use_flags & ON_LEFT_HAND))
			desc= desc  + " on left hand";
		else if(0 != (obj.in_use_flags & ON_RIGHT_HAND))
			desc= desc  + " on right hand";
		return desc;
	}
	static int gr_species_old(Randomx rand){
		int species= RING;
		int percent= rand.get(1, 91);

		if(percent <= 30)		species= SCROLL;	//30 33
		else if(percent <= 60)	species= POTION;	//30 33
		else if(percent <= 64)	species= WAND;		// 4  5
		else if(percent <= 74)	species= WEAPON;	//10 11
		else if(percent <= 83)	species= ARMOR;		// 9 10
		else if(percent <= 88)	species= FOOD;		// 5  6
		return species;								// 3  2
	}
	static int gr_species(Randomx rand){
		int species;
		int percent= rand.get(100);
		if(percent<27)	species= POTION;		//27 33
		else if(percent<57)	species= SCROLL;	//30 33
		else if(percent<74)	species= FOOD;		//17  6
		else if(percent<82)	species= WEAPON;	// 8 11
		else if(percent<90)	species= ARMOR;		// 8 10
		else if(percent<95)	species= RING;		// 5  2
		else species= WAND;						// 5  4
		return species;
	}
	static int gr_which_scroll(Randomx rand){
		int percent= rand.get(Monster.isold? 100 : 91);
		int k= 0;
		if(percent <= 5)				k= PROTECT_ARMOR;
		else if(percent <= 10)			k= HOLD_MONSTER;
		else if(percent <= 20)			k= CREATE_MONSTER;
		else if(percent <= 35)			k= IDENTIFY;
		else if(percent <= 43)			k= TELEPORT;
		else if(percent <= 50)			k= SLEEP;
		else if(percent <= 55)			k= SCARE_MONSTER;
		else if(percent <= 64)			k= REMOVE_CURSE;
		else if(percent <= 69)			k= ENCH_ARMOR;
		else if(percent <= 74)			k= ENCH_WEAPON;
		else if(percent <= 80)			k= AGGRAVATE_MONSTER;
		else if(percent <= 86)			k= CON_MON;
		else if(percent <= 91)			k= MAGIC_MAPPING;
		else if(percent <= 92)			k= BLANK_PAPER;
		else k= LIGHT;
		return k;
	}
	static int gr_which_potion(Randomx rand){
		int percent= rand.get(118);
		int k= 0;
		if(percent <= 5)				k= RAISE_LEVEL;
		else if(percent <= 15)			k= DETECT_TOYS;
		else if(percent <= 25)			k= DETECT_MONSTER;
		else if(percent <= 35)			k= INCREASE_STRENGTH;
		else if(percent <= 45)			k= RESTORE_STRENGTH;
		else if(percent <= 55)			k= HEALING;
		else if(percent <= 65)			k= EXTRA_HEALING;
		else if(percent <= 75)			k= BLINDNESS;
		else if(percent <= 85)			k= HALLUCINATION;
		else if(percent <= 95)			k= CONFUSION;
		else if(percent <= 105)			k= POISON;
		else if(percent <= 110)			k= LEVITATION;
		else if(percent <= 114)			k= HASTE_SELF;
		else 							k= SEE_INVISIBLE;
		return k;
	}
	static void identify(int kind){
		Id id_table[]= get_id_table(kind);
		if(id_table!=null)
			id_table[kind & 255].id_status= IDENTIFIED;
	}
	static void identify_uncalled(int kind){
		Id id_table[]= get_id_table(kind);
		if(id_table!=null){
			if(id_table[kind&255].id_status != CALLED)
				id_table[kind&255].id_status= IDENTIFIED;
		}
	}
	static void wizard_identify(){
		for(int species= ARMOR; species<=ALL_TOYS; species *= 2){
			Id id_table[]= get_id_table(species);
			if(id_table != null)
			for(int k= 0; k<id_table.length; k++)
				identify(species+k);
		}
	}
	static String toy_chars= "%!?]=/):*";
	static char gr_obj_char(Randomx rand){
		return toy_chars.charAt(rand.get(toy_chars.length()-1));
	}
	static void id_type(Man man){
		String id= "unknown character";
		int ch;
		man.tell("what do you want identified?");
		ch= man.self.rgetchar();
		if((ch >= 'A') && (ch <= 'Z')){
			id= Monster.mon_tab[ch-'A'].m_name;
		} else if(ch < 32){
			ch= '?';
		} else {
			switch(ch){
			case '$':	id= "unidentified monster"; break;
			case '@':	id= "human"; break;
			case '%':	id= "staircase"; break;
			case '^':	id= "trap"; break;
			case '+':	id= "door"; break;
			case '-':
			case '|':	id= "wall of a room"; break;
			case '.':	id= "floor"; break;
			case '#':	id= "passage"; break;
			case ' ':	id= "solid rock"; break;
			case '=':	id= "ring"; break;
			case '?':	id= "scroll"; break;
			case '!':	id= "potion"; break;
			case '/':	id= "wand or staff"; break;
			case ')':	id= "weapon"; break;
			case ']':	id= "armor"; break;
			case '*':	id= "gold"; break;
			case ':':	id= "food"; break;
			case ',':	id= "the Amulet of Yendor"; break;
			case 'o':	if(man.self.treepct>0)id= "oak tree"; break;
			}
		}
		man.view.msg.check_message();
		man.tell((char)ch + " : " + id);
	}
	static int parse_damage(String s)[]{
		int ans[]= new int[2];
		int d= s.indexOf('d');
		if(d>0){
			ans[0]= Integer.parseInt(s.substring(0,d));
			ans[1]= Integer.parseInt(s.substring(d+1));
		}else{
			ans[0]= Integer.parseInt(s);
			ans[1]= 0;
		}
		return ans;
	}
	static int get_damage(String ds, Randomx rand){
		int total= 0;
		StringTokenizer st= new StringTokenizer(ds, "/d", false);
		while(st.hasMoreTokens()){
			int n =Integer.parseInt(st.nextToken());
			int d= Integer.parseInt(st.nextToken());
			for(int j= 0; j<n; j++)
				total += rand!=null? rand.get(1, d) : d;
		}
		return total;
	}
	public String toString(){
		return "v="+value+ " "+title+" "+real+" "+id_status;
	}
}
