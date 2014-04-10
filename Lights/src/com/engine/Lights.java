
package com.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

public class Lights
{

	static int DimensionX;
	static int DimensionY;
	
	
	public static void main (String[] args)
	{

		runGame(3,3);

	}

	private static void runGame (int i, int j)
	{

		DimensionX=i;
		DimensionY=j;
		
		// load the board
		boolean vic = false;
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map = initMap();
		System.out.println(map);
		printMap(map);
		Scanner sc = new Scanner(System.in);

		// start game
		while (vic == false)
		{
			// user plays
			int x = sc.nextInt();
			int y = sc.nextInt();

			map = repaintMap(map, x, y);
// System.out.println(map);
			printMap(map);

			// check for vic

			if (!map.containsValue(true))
			{
				vic = true;
			}

		}

	}

	private static void printMap (Map<String, Boolean> map)
	{

		for (int i = 1; i <= DimensionX; i++)
		{
			for (int j = 1; j <= DimensionY; j++)
			{
				String result = "";
				if (map.containsKey("r" + i + "c" + j))
				{
					if (map.get("r" + i + "c" + j))
					{
						result = "X";
					}
					else
					{
						result = "O";
					}
				}
				System.out.print(result + "\t");
			}
			System.out.println("");
		}

	}

	private static Map<String, Boolean> repaintMap (Map<String, Boolean> map, int x, int y)
	{

		String inputStr = "r" + x + "c" + y;

		String north = "";
		String south = "";
		String east = "";
		String west = "";

		List<String> strList = new ArrayList<String>();
		strList.add(inputStr);
		// find if the neighbours exist
		if (y - 1 != 0)
		{
			west = "r" + x + "c" + (y - 1);
			strList.add(west);
		}
		if (y + 1 != (DimensionY+1))
		{
			east = "r" + x + "c" + (y + 1);
			strList.add(east);
		}
		if (x - 1 != 0)
		{
			north = "r" + (x - 1) + "c" + y;
			strList.add(north);
		}
		if (x + 1 != (DimensionX+1))
		{
			south = "r" + (x + 1) + "c" + y;
			strList.add(south);
		}

		Iterator<String> itr = strList.iterator();
		while (itr.hasNext())
		{
			String input = itr.next();
// System.out.println(map);
			boolean value;
			if (map.containsKey(input))
			{
				value = map.get(input);
				map.put(input, !value);
			}

		}

		return map;
	}

	private static Map<String, Boolean> initMap ()
	{

		Map<String, Boolean> initMap = new HashMap<String, Boolean>();

		for (int i = 1; i < (DimensionX+1); i++)
		{
			for (int j = 1; j < (DimensionY+1); j++)
			{
				initMap.put("r" + i + "c" + j, false);
//				System.out.println("initMap"+initMap);
			}
		}

		int difficulty = 3;
		initMap = randomMapGen(difficulty, initMap);

		return initMap;
	}

	private static Map<String, Boolean> randomMapGen (int difficulty, Map<String, Boolean> initMap)
	{

		for (int k = 0; k < difficulty; k++)
		{
			int x = getRandomFactor(1, DimensionX);
			int y = getRandomFactor(1, DimensionY);
//			System.out.println("x:" + x + "\ny:" + y);
			initMap = repaintMap(initMap, x, y);
//			System.out.println(initMap);
		}
		return initMap;
	}

	private static int getRandomFactor (int Min, int Max)
	{

		return (Min + (int)(Math.random() * ((Max - Min) + 1)));
	}

}
