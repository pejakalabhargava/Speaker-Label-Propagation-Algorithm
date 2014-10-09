package com.myapp.slpa.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Data structure the hold the undirected graph on which clustering algorithm is run to
 * find the overlapping communities using SLPA algorithm.
 * @author pejakalabhargava
 *
 */
public class Graph {
	
	//Number of edges in the graph
	int edges;
	
	//Number of vertices of the graph
	int vertices;
	
	//This holds the id of the node and the reference to corresponding Node refernce
	Map<Integer, Node> graphADT;
	
	// This is used to hold the final communities that are calculated.Key of the
	// map is the labelId of the community and value is the set of integers
	// representing the node Id's present in that community.
	Map<Integer,Set<Integer>> overlappingCommunities;

	// Constructor to create the graph which takes the filename as input.
	// Firs line consists of nubmer of vertices and number of edges sepearetd by
	// space.Subsequent lines consists of edges in the network represented as
	// "from to".
	public Graph(String filepath) {
		graphADT = new LinkedHashMap<Integer, Node>();
		overlappingCommunities =  new HashMap<Integer, Set<Integer>>();
		readGraph(filepath);
	}

	/**
	 * 
	 * @param filepath
	 */
	private void readGraph(String filepath) {
		System.out.println("Reading from the input file and creating graph...");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
			String line;
			// Get number of vertices and edges
			if ((line = br.readLine()) != null) {
				String[] config = line.split(" ");
				vertices = Integer.parseInt(config[0]);
				edges = Integer.parseInt(config[1]);
				System.out.println("Number of vertices and edges are:" + vertices + " and " + edges);
			} else {
				System.out.println("Invalid file input.Exiting....");
				System.exit(0);
			}
			// Get source and destination of each edge and add it to the
			// neighbour list of the node.
			while ((line = br.readLine()) != null) {
				String[] config = line.split(" ");
				int source = Integer.parseInt(config[0]);
				int dest = Integer.parseInt(config[1]);
				addEdge(source, dest);
			}
		} catch (IOException e) {
			System.out.println("Error Reading from the file.Exiting....");
			System.exit(0);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		// This is an extra step to validate that correct values are read from
		// the file since we calculate the degree and then cross check if
		// degree/2 is equal to number of edges
		int no = 0;
		for (Map.Entry<Integer, Node> entry : graphADT.entrySet()) {
			Node value = entry.getValue();
			Set<Node> neigh = value.getNeighbhours();
			no += neigh.size();
		}
		if(no/2 != edges) {
			System.out.println("Error in reading the file contents.It seems that number of edges input and entry are mismatching");
		}
		System.out.println("Creation of the graph complete.");
	}

	/**
	 * This method adds an edge into the graph data structure.make sure to add two entries since the graph is
	 * undirected.
	 * @param source
	 * @param dest
	 */
	private void addEdge(Integer source, Integer dest) {
		//Check if source node exists else create a new one.
		Node sourceNode = graphADT.get(source);;
		if (sourceNode == null) {	
			sourceNode = new Node(source);
			graphADT.put(source, sourceNode);
		}
		//Check if destintation node exists else create a new one.
		Node destNode = graphADT.get(dest);
		if (destNode == null) {
			destNode = new Node(dest);
			graphADT.put(dest, destNode);
		}
		//Add an entry into the adjacenecy list.
		sourceNode.addNeighbour(destNode);
		destNode.addNeighbour(sourceNode);
	}

	/**
	 * Gets the node based on nodeId
	 * @param nodeId
	 * @return integer
	 */
	public Node getNode(int nodeId) {
		return graphADT.get(nodeId);
	}

	/**
	 * Gets number of edges in the undirected graph
	 * @return integer
	 */
	public int getNumberOfEdges() {
		return edges;
	}

	/**
	 * Gets number of vertices in the undirected graph
	 * @return integer
	 */
	public int getNumberVertices() {
		return vertices;
	}

	/**
	 * Returns the map holding the final communities.
	 * @return community map
	 */
	public Map<Integer, Set<Integer>> getOverlappingCommunities() {
		return overlappingCommunities;
	}
}
