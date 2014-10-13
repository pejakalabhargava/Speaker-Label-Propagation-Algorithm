Speaker-Label-Propagation-Algorithm
===================================

Speaker Label Propagation Algorithm for detecting communites in a graph. 

1.MINIMUM REQUIRED SOFTWARE:
	Apache Maven 3.0.5 (mvn -version) 
	Java 1.7.0_67      (java -version)

2.INSTRUCTIONS ON HOW TO RUN THE PROGRAM
	a.Go to the root folder of the project where pom.xml is present
		cd slpa
	b.Do a maven build
		mvn clean install
	c.Run the SLPA algorithm with the arguments as: 
		mvn exec:java -Dexec.args="<input_graph_path> <output_graph_path> <number_of_iterations> <threshold>"
		exampe:mvn exec:java -Dexec.args="amazon.graph.original amazon.graph.comm 20 0.5"

3.ALTERNATE INSTRUCTIONS ON HOW TO RUN THE PROGRAM
	a.Go to the root folder of the project where pom.xml is present
		cd slpa
	b.Do a maven build
		mvn clean install
	c.Go to the folder where jar is located after maven build
		cd target
	d.Run the SLPA algorithm from the jar
		java -jar slpa-0.0.1.jar <input_graph_path> <output_graph_path> <number_of_iterations> <threshold>
		example:java -jar slpa-0.0.1.jar amazon.graph.original amazon.graph.comm 20 0.5
		
4.INSTRUCTIONS ON HOW TO INTERPRET THE RESULTS
	The output will be the communities that are found by running the SLPA algorithm.Each line in the output
	file represents a community with nodes delimited by a space character.Each community is represented in the 
	new line.
	EXAMPLE OUTPUT:
	0 1 2 3 4 5 6 7 8 9 10 11 
	16 12 13 14 15 
	
5. SAMPLE INPUT AND OUTPUT FILES.
	Check the Results folder for sample input and output.
	a.Input file in the first line consist of number of vertices(m) and number of edges(n) delimited by space.
	  Rest of the lines consists of the all the edges represented as "from to".
	  Example:
		16716 48739
		0 1
		0 2
		0 3
		0 4
		0 5
		0 6
		7 8
		7 9
		7 10
		7 11
		....
		....
		
	b.Output will be the communities determined out from the graph as mentioned in Step 4.
	