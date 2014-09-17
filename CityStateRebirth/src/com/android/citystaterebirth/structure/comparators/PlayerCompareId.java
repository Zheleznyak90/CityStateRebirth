package com.android.citystaterebirth.structure.comparators;

import java.util.Comparator;

import com.android.citystaterebirth.structure.Player;

public class PlayerCompareId implements Comparator<Player>{

	@Override
	public int compare(Player o1, Player o2) {
		return new Integer(o1.getPlayerId()).compareTo(new Integer(o2.getPlayerId()));
	}

}
