package genius;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class Tagger {
	private static String text;
	
	public Tagger (String input) {
		text = input;
		
	}
	
	public static String tag() throws ClassNotFoundException, IOException {
		MaxentTagger tagger = new MaxentTagger("taggers\\bidirectional-distsim-wsj-0-18.tagger");
		//Now, we have all of the data, now what we have to do is go through all of the sentences in the data and find the average amount of words
		//that are specific POS
		
		//Activate the tagger by doing tagger.tagString(string);
		//Output is to a string, printable, with formatting like "This/DT is/VBZ sample/NN text/NN"
		
		double wordCount = 0;
		int lastIndex = 0;
		
		double noun = 0, coordinatingConjunction = 0, cardinalNumber = 0, determiner = 0, existentialThere = 0, foreignWord = 0;
		double subordinatingConjunction = 0, subordinator = 0, adjective = 0, comparativeAdjective = 0, superlativeAdjective = 0, listMarker = 0, modal = 0;
		double nounPlural = 0, nounProper = 0, nounPluralProper = 0, predeterminer = 0, posessiveEnding = 0, personalPronoun = 0, possessivePronoun = 0, adverb = 0;
		double comparativeAdverb = 0, superlativeAdverb = 0, particle = 0, punctiation = 0, symbol = 0, infinitiveTo = 0, interjection = 0, verbBeBase = 0;
		double verbBePast = 0, verbBePresentPart = 0, verbBePastPart = 0, verbBePresnon3d = 0, verbBePres3d = 0, whDeterminer = 0, whPronoun = 0;
		double possesiveWhPronoun = 0, whAdverb = 0, comma = 0, predicatePronoun = 0;
		System.out.println("Tagging started");
		String taggedSampleString = "";
		try {
			taggedSampleString = tagger.tagString(text + ".");
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
		System.out.println("Tagging finished");
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/,",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/,".length());
		    	comma ++;
		        lastIndex += "/,".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("//",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "//".length());
		        lastIndex += "//".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/-",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/-".length());
		        lastIndex += "/-".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/:",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/:".length());
		        lastIndex += "/:".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/(",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/(".length());
		        lastIndex += "/(".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/)",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/)".length());
		        lastIndex += "/)".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/_",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/_".length());
		        lastIndex += "/_".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/&",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/&".length());
		        lastIndex += "/&".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/$",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/$".length());
		        lastIndex += "/$".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/;",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/;".length());
		        lastIndex += "/;".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/.",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/.".length());
		        lastIndex += "/.".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/. ",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/. ".length());
		        lastIndex += "/. ".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/`",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/`".length());
		    	lastIndex += "/`".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/``",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/``".length());
		    	lastIndex += "/``".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/'",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/'".length());
		    	lastIndex += "/'".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/''",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/''".length());
		    	lastIndex += "/''".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/\"\"",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/\"\"".length());
		    	lastIndex += "/\"\"".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/\"",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/\"".length());
		    	lastIndex += "/\"".length();
		    }
		}
		System.out.println("Done with punctiation");
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/NNS",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/NNS".length());
		    	nounPlural ++;
		    	wordCount ++;
		        lastIndex += "/NNS".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/NN",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/NN".length());
		    	noun ++;
		    	wordCount ++;
		        lastIndex += "/NN".length();
		    }
		}
		System.out.println("Nouns done.");
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/CC",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/CC".length());
		    	coordinatingConjunction ++;
		    	wordCount ++;
		        lastIndex += "/CC".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/CD",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/CD".length());
		    	cardinalNumber ++;
		    	wordCount ++;
		        lastIndex += "/CD".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/DT",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/DT".length());
		    	determiner ++;
		    	wordCount ++;
		        lastIndex += "/DT".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/EX",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/EX".length());
		    	existentialThere ++;
		    	wordCount ++;
		        lastIndex += "/EX".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/FW",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/FW".length());
		    	foreignWord ++;
		    	wordCount ++;
		        lastIndex += "/FW".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/IN/that",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/IN/that".length());
		    	subordinator ++;
		    	wordCount ++;
		        lastIndex += "/IN/that".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/IN",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/IN".length());
		    	subordinatingConjunction ++;
		    	wordCount ++;
		        lastIndex += "/IN".length();
		    }
		}
		System.out.println("Extraneous stuff done.");
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/JJR",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/JJR".length());
		    	comparativeAdjective ++;
		    	wordCount ++;
		        lastIndex += "/JJR".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/JJS",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/JJS".length());
		    	superlativeAdjective ++;
		    	wordCount ++;
		        lastIndex += "/JJS".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/JJ",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/JJ".length());
		    	adjective ++;
		    	wordCount ++;
		        lastIndex += "/JJ".length();
		    }
		}
		System.out.println("Adjectives done.");
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/LS",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/LS".length());
		    	listMarker ++;
		    	wordCount ++;
		        lastIndex += "/LS".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/MD",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/MD".length());
		    	modal ++;
		    	wordCount ++;
		        lastIndex += "/MD".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/NPS",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/NPS".length());
		    	nounPluralProper ++;
		    	wordCount ++;
		        lastIndex += "/NPS".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/NP",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/NP".length());
		    	nounProper ++;
		    	wordCount ++;
		        lastIndex += "/NP".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/PDT",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/PDT".length());
		    	predeterminer ++;
		    	wordCount ++;
		        lastIndex += "/PDT".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/POS",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/POS".length());
		    	posessiveEnding ++;
		    	wordCount ++;
		        lastIndex += "/POS".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/PPZ",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/PPZ".length());
		    	possessivePronoun ++;
		    	wordCount ++;
		        lastIndex += "/PPZ".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/PP",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/PP".length());
		    	personalPronoun ++;
		    	wordCount ++;
		        lastIndex += "/PP".length();
		    }
		}
		System.out.println("Pronouns done.");
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/RBR",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/RBR".length());
		    	comparativeAdverb ++;
		    	wordCount ++;
		        lastIndex += "/RBR".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/RBS",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/RBS".length());
		    	superlativeAdverb ++;
		    	wordCount ++;
		        lastIndex += "/RBS".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/RB",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/RB".length());
		    	adverb ++;
		    	wordCount ++;
		        lastIndex += "/RB".length();
		    }
		}
		System.out.println("Adverbs done.");
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/RP",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/RP".length());
		    	particle ++;
		    	wordCount ++;
		        lastIndex += "/RP".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/SENT",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/SENT".length());
		    	punctiation ++;
		    	wordCount ++;
		        lastIndex += "/SENT".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/SYM",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/SYM".length());
		    	symbol ++;
		    	wordCount ++;
		        lastIndex += "/SYM".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/TO",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/TO".length());
		    	infinitiveTo ++;
		    	wordCount ++;
		        lastIndex += "/TO".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/UH",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/UH".length());
		    	interjection ++;
		    	wordCount ++;
		        lastIndex += "/UH".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/VBD",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/VBD".length());
		    	verbBePast ++;
		    	wordCount ++;
		        lastIndex += "/VBD".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/VBG",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/VBG".length());
		    	verbBePresentPart ++;
		    	wordCount ++;
		        lastIndex += "/VBG".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/VBN",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/VBN".length());
		    	verbBePastPart ++;
		    	wordCount ++;
		        lastIndex += "/VBN".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/VBP",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/VBP".length());
		    	verbBePresnon3d ++;
		    	wordCount ++;
		        lastIndex += "/VBP".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/VBZ",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/VBZ".length());
		    	verbBePres3d ++;
		    	wordCount ++;
		        lastIndex += "/VBZ".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/VB",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/VB".length());
		    	verbBeBase ++;
		    	wordCount ++;
		        lastIndex += "/VB".length();
		    }
		}
		System.out.println("Verbs are done.");
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/WDT",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/WDT".length());
		    	whDeterminer ++;
		    	wordCount ++;
		        lastIndex += "/WDT".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/WP$",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/WP$".length());
		    	possesiveWhPronoun ++;
		    	wordCount ++;
		        lastIndex += "/WP$".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/WP",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/WP".length());
		    	whPronoun ++;
		    	wordCount ++;
		        lastIndex += "/WP".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/WRB",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/WRB".length());
		    	whAdverb ++;
		    	wordCount ++;
		        lastIndex += "/WRB".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/PRP$",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/PRP$".length());
		        possessivePronoun ++;
		        wordCount ++;
		    	lastIndex += "/PRP$".length();
		    }
		}
		lastIndex = 0;
		while(lastIndex != -1){
		    lastIndex = taggedSampleString.indexOf("/PRP",lastIndex);
		    if(lastIndex != -1){
		    	taggedSampleString = taggedSampleString.substring(0, lastIndex) + taggedSampleString.substring(lastIndex + "/PRP".length());
		        predicatePronoun ++;
		        wordCount ++;
		    	lastIndex += "/PRP".length();
		    }
		}
		System.out.println("Done with cutting");
		String retrun;
		System.out.println("Scanning done");
		System.out.println("Word Count: " + wordCount);
		System.out.println("Analytics:");
		System.out.println("--------------------------");
		System.out.println("Adjectives:");
		System.out.println("Base Adjectives: " + ((adjective/wordCount))*100 + "%");
		System.out.println("Comparative Adjectives: " + ((comparativeAdjective/wordCount))*100 + "%");
		System.out.println("Superlative Adjectives: " + ((superlativeAdjective/wordCount))*100 + "%");
		System.out.println("--------------------------");
		System.out.println("Nouns:");
		System.out.println("Base Nouns: " + ((noun/wordCount))*100 + "%");
		System.out.println("Plural Nouns: " + ((nounPlural/wordCount))*100 + "%");
		System.out.println("--------------------------");
		System.out.println("Pronouns:");
		System.out.println("Possessive Pronouns: " + ((possessivePronoun/wordCount))*100 + "%");
		System.out.println("Wh-Pronouns: " + ((whPronoun/wordCount))*100 + "%");
		System.out.println("Possessive Wh-Pronouns: " + ((possesiveWhPronoun/wordCount))*100 + "%");
		System.out.println("Predicate Pronouns: " + ((predicatePronoun/wordCount))*100 + "%");
		System.out.println("--------------------------");
		System.out.println("Adverbs:");
		System.out.println("Base Adverbs: " + ((adverb/wordCount))*100 + "%");
		System.out.println("Comparative Adverbs: " + ((comparativeAdverb/wordCount))*100 + "%");
		System.out.println("Superlative Adverbs: " + ((superlativeAdverb/wordCount))*100 + "%");
		System.out.println("Wh-Adverbs: " + ((whAdverb/wordCount))*100 + "%");
		System.out.println("--------------------------");
		System.out.println("Verbs:");
		System.out.println("Base Verb: " + ((verbBeBase/wordCount))*100 + "%");
		System.out.println("Verb Past Tense: " + ((verbBePast/wordCount))*100 + "%");
		System.out.println("Verb Past Participle: " + ((verbBePastPart/wordCount))*100 + "%");
		System.out.println("Verb Present Participle: " + ((verbBePresentPart/wordCount))*100 + "%");
		System.out.println("Verb Present Non-3d: " + ((verbBePresnon3d/wordCount))*100 + "%");
		System.out.println("Verb Present 3d: " + ((verbBePres3d/wordCount))*100 + "%");
		System.out.println("--------------------------");
		System.out.println("Misc:");
		System.out.println("Coodinating Conjunctions: " + ((coordinatingConjunction/wordCount))*100 + "%");
		System.out.println("Cardinal Numbers: " + ((cardinalNumber/wordCount))*100 + "%");
		System.out.println("Determiners: " + ((determiner/wordCount))*100 + "%");
		System.out.println("Existential Theres: " + ((existentialThere/wordCount))*100 + "%");
		System.out.println("Foreign Words: " + ((foreignWord/wordCount))*100 + "%");
		System.out.println("Subordinating Conjunctions: " + ((subordinatingConjunction/wordCount))*100 + "%");
		System.out.println("That as a Subordinator: " + ((subordinator/wordCount))*100 + "%");
		System.out.println("List Markers: " + ((listMarker/wordCount))*100 + "%");
		System.out.println("Modals: " + ((modal/wordCount))*100 + "%");
		System.out.println("Predeterminers: " + ((predeterminer/wordCount))*100 + "%");
		System.out.println("Possessive Endings: " + ((posessiveEnding/wordCount))*100 + "%");
		System.out.println("Particles: " + ((particle/wordCount))*100 + "%");
		System.out.println("Punctuations: " + ((punctiation/wordCount))*100 + "%");
		System.out.println("Symbols: " + ((symbol/wordCount))*100 + "%");
		System.out.println("Infinitive To: " + ((infinitiveTo/wordCount))*100 + "%");
		System.out.println("Interjections: " + ((interjection/wordCount))*100 + "%");
		System.out.println("Wh-Determiner: " + ((whDeterminer/wordCount))*100 + "%");
		System.out.println("Commas: " + ((comma/wordCount))*100 + "%");
		retrun = "Scanning done \n";
		retrun += "Word Count: " + wordCount;
		retrun += "\n Analytics \n " + "Base adjectives: " + ((adjective/wordCount)*100) + "%\n Comparative Adjectives: " + ((comparativeAdjective/wordCount)*100) + "%\n Superlative Adjectives: " + ((superlativeAdjective/wordCount)*100) + "%\n Base nouns: " + ((noun/wordCount)*100) + "%\n Plural Nouns: " + ((nounPlural/wordCount)*100);
		retrun += "%\n Possessive Pronouns: " + ((possessivePronoun/wordCount)*100) + "%\n Wh-pronouns: " + ((whPronoun/wordCount)*100) + "%\n Possessive Wh-Pronouns: " + ((possesiveWhPronoun/wordCount)*100) + "%\n Predicate Pronouns: " + ((predicatePronoun/wordCount)*100) + "%\n Base Adverbs: " + ((adverb/wordCount)*100);
		retrun += "%\n Comparative Adverbs: " + ((comparativeAdverb/wordCount)*100) + "%\n Superlative Adverbs: " + ((superlativeAdverb/wordCount)*100) + "%\n Wh-Adverbs: " + ((whAdverb/wordCount)*100) + "%\n Base Verbs: " + ((verbBeBase/wordCount)*100) + "%\n Verb Past Tense: " + ((verbBePast/wordCount)*100);
		retrun += "%\n Verb Past Participle: " + ((verbBePastPart/wordCount)*100) + "%\n Verb Present Participle: " + ((verbBePresentPart/wordCount)*100) + "%\n Verb Present Non-3d: " + ((verbBePresnon3d/wordCount)*100) + "%\n Verb Present 3d: " + ((verbBePres3d/wordCount)*100);
		retrun += "%\n Coordinating Conjunctions: " + ((coordinatingConjunction/wordCount)*100) + "%\n Determiners: " + ((determiner/wordCount)*100) + "%\n Existential Theres: " + ((existentialThere/wordCount)*100);
		retrun += "%\n Subordinating Conjunctions: " + ((subordinatingConjunction/wordCount)*100) + "%\n Predeterminers: " + ((predeterminer/wordCount)*100) + "%\n Infinitive To: " + ((infinitiveTo/wordCount)*100) + "%\n Wh-Determiners: " + ((whDeterminer/wordCount)*100);
		return retrun;
	}
	
	public static String[] getProject(String filePath) {
		String everything = "";
		String[] arr = new String[3];
		try(BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    everything = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error with bufferedreader");
		}
		
		try {
			everything = everything.substring(everything.indexOf("***"), everything.indexOf("*** END"));
		} catch (Exception e) {
			try{
				everything = everything.substring(everything.indexOf("***"), everything.indexOf("***END"));
			} catch (Exception g) {
				try {
					everything = everything.substring(everything.indexOf("***"), everything.indexOf("This file should be named"));
				} catch (Exception f) {
					everything = everything;
					System.out.println("Classic extrapolation didn't work.");
				}
			}
		}
		int total = everything.length();
		
		arr[0] = filePath;
		arr[1] = everything;
		arr[2] = Integer.toString(total);
		
		return arr;
	}
}