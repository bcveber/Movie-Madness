/*

 IMDbQuery.java
 COSC 102, Colgate University

 Implements a sparse matrix using hash tables.
 Your code goes here.
 See instructions for explanation of methods.

*/

import java.io.*;
import java.util.*;

public class IMDbQuery
{

	
	HashMap<String, HashSet<String>> sortActor; //actor is the key
	HashMap<String, HashSet<String>> sortMovie; //movie is the key
	


  //Constructor
  //Gets passed all of the provided read in data
  //in the form of an ArrayList of String arrays.
  //Each string array represents one line of the source data
  //split on the forward slashes '/'.
 public IMDbQuery(ArrayList<String[]> data)
 {
 
 	sortActor = new HashMap<String, HashSet<String>>();
 	sortMovie = new HashMap<String, HashSet<String>>();
 	HashSet<String> tempMovieForActor;
 
 	for (int i = 0; i < data.size(); i++){ //runs through file
 		String[] movie = data.get(i);
 		HashSet<String> actorHash = new HashSet<String>();
 		for (int x = 1; x < movie.length; x++){
 			actorHash.add(movie[x]);
 			//System.out.println(movie[x]);
 			if (sortActor.containsKey(movie[x])){
 				tempMovieForActor = sortActor.get(movie[x]);
 			}
 			else{
 				tempMovieForActor = new HashSet<String>();
 			}
 			tempMovieForActor.add(movie[0]);
 			sortActor.put(movie[x], tempMovieForActor);
 		}
 		sortMovie.put(movie[0], actorHash);
 	}
 			
 			
 		
}

 // Returns a collection of the movies that the argument actor has appeared in. 
 // Must run in O(m) time where m is the number of movies the given actor has appeared in.
 public Iterable<String> getMovies(String actor)
 {
  return sortActor.get(actor);
 }

 
 // Returns a collection of actors that have appeared in the argument movie. 
 // Must run in O(a) time where a is the number of actors that appeared in the given movie. 
 public Iterable<String> getActors(String movie)
 {
  return sortMovie.get(movie);
 }

 
 // Returns a collection of actors that have appeared in any film with the argument actor. 
 // Must run in O(c) time where c is the total number of non-unique costars of the actor.
 public Iterable<String> getCoStars(String actor)
 {
 	 HashSet<String> coMovies = sortActor.get(actor); //movies the actor appeared in 
 	 HashSet<String> temp = new HashSet<String>();
 	 HashMap<String, HashSet<String>> coStars = new HashMap<String, HashSet<String>>();
 	 
 	 for (String individualMovie: coMovies){
 	 	 //System.out.println(sortMovie.get(individualMovie));
 	 	 //System.out.println("----");
 	 	 HashSet<String> allTheCoStars = sortMovie.get(individualMovie); //all the actors from the movies the main actor appeared in 
 	 	 //System.out.println(allTheCoStars);
 	 	 for (String individualCoStar: allTheCoStars){ //preparing the hashset
 	 	 	 if (!individualCoStar.equals(actor)){ //to make sure it doesn't add the star in the coStars
 	 	 	 	temp.add(individualCoStar);
 	 	 	 }
 	 	 }
 	 }
 	 
 	 coStars.put(actor,temp); //star actor and hashset of all the costars
 	 
 	 return coStars.get(actor);
 }

 
 // Returns a boolean indicating whether the argument actor appeared in the argument movie. 
 // Must run in O(1) time.
 public boolean isMatch(String actor, String movie)
 {
 	HashSet<String> matcher = sortActor.get(actor);
 	
 	if (matcher.contains(movie)){
 			return true;
 	}
 	else{
 		return false;
 	}
 }

 
 // Returns a collection of all the movies in which the two argument actors have appeared together. 
 // Must run in O(a1 + a2) time, where a1 and a2 are the total number of movies actor1 and actor2 have appeared in, respectively.
 public Iterable<String> getMovieLinks(String actor1, String actor2)
 {
 	 HashSet<String> actorOne = sortActor.get(actor1);
 	 HashSet<String> actorTwo = sortActor.get(actor2);
 	 HashSet<String> moviesTogether = new HashSet<String>();
 	 
 	 for(String individualMovie: actorOne){
 	 	 if (actorTwo.contains(individualMovie)){
 	 	 		 moviesTogether.add(individualMovie);
 	 	 }
 	 }
 	 	 		 
 	 
  return moviesTogether;
 }
 
 
}

