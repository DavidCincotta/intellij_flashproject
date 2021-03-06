import java.util.Vector;
import java.io.*;
import java.util.Enumeration;

public class Item extends Rowcol implements Header, Serializable {
	private static final long serialVersionUID= 3L;
	char ichar;				/* 'A' is for aquatar */
	Level level;

	Item(){
		level= null;
	}
	Item(Level level, int r, int c){
		this.level= level;
		row= r;
		col= c;
		ichar= '?';
	}
	Item(Level level){
		this(level, 0,0);
	}
	void place_at(int r, int c, int what){
		Vector list= null;
		switch(what){
		case TOY:		list= level.level_toys; break;
		case MONSTER:	list= level.level_monsters; break;
		case MAN:		list= level.level_men; break;
		case TRAP:		list= level.level_traps; break;
		case DOOR:		list= level.level_doors; break;
		}
		if(list != null){
			if(list.contains(this)){
				level.mark(row, col);
				level.map[row][col] &= ~what;
			}else if(r>=0)
				list.addElement(this);
		}
		row= r;
		col= c;
		if(r>0){
			level.mark(r, c);
			level.map[r][c] |= what;
		}else if(list != null)
			list.removeElement(this);
	}
	public String toString(){
		return super.toString() + Integer.toString(ichar>>8) + ((char)(ichar&255)) + " ";
	}
}
