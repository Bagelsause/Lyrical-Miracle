package edu.stanford.nlp.tagger.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.io.NumberRangesFileFilter;
import edu.stanford.nlp.tagger.maxent.TaggerConfig;
import edu.stanford.nlp.trees.TreeTransformer;
import edu.stanford.nlp.trees.TreeNormalizer;
import edu.stanford.nlp.util.ReflectionLoading;

public class TaggerFileRecord {
  enum Format {
    TEXT,  // represents a tokenized file separated by text
    //TSV,   // represents a tsv file such as a conll file
    TREES; // represents a file in PTB format
  };

  final String file;
  final Format format;
  final String encoding;
  final String tagSeparator;
  final TreeTransformer treeTransformer;
  final TreeNormalizer treeNormalizer;
  final NumberRangesFileFilter treeRange;

  private TaggerFileRecord(String file, Format format, 
                           String encoding, String tagSeparator,
                           TreeTransformer treeTransformer,
                           TreeNormalizer treeNormalizer,
                           NumberRangesFileFilter treeRange) {
    this.file = file;
    this.format = format;
    this.encoding = encoding;
    this.tagSeparator = tagSeparator;
    this.treeTransformer = treeTransformer;
    this.treeNormalizer = treeNormalizer;
    this.treeRange = treeRange;
  }

  static final String FORMAT = "format";
  static final String ENCODING = "encoding";
  static final String TAG_SEPARATOR = "tagSeparator";
  static final String TREE_TRANSFORMER = "treeTransformer";
  static final String TREE_NORMALIZER = "treeNormalizer";
  static final String TREE_RANGE = "treeRange";

  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(FORMAT + "=" + format);
    s.append("," + ENCODING + "=" + encoding);
    s.append("," + TAG_SEPARATOR + "=" + tagSeparator);
    if (treeTransformer != null) {
      s.append("," + TREE_TRANSFORMER + "=" + 
               treeTransformer.getClass().getName());
    }
    if (treeNormalizer != null) {
      s.append("," + TREE_NORMALIZER + "=" + 
               treeNormalizer.getClass().getName());               
    }
    if (treeRange != null) {
      s.append("," + TREE_RANGE + "=" + treeRange);               
    }
    return s.toString();
  }

  public String filename() { return file; }

  public TaggedFileReader reader() {
    switch(format) {
    case TEXT:
      return new TextTaggedFileReader(this);
    case TREES:
      return new TreeTaggedFileReader(this);
    default:
      throw new IllegalArgumentException("Unknown format " + format);
    }
  }

  static public List<TaggerFileRecord> createRecords(Properties config,
                                                     String description) {
    String[] pieces = description.split(";");
    List<TaggerFileRecord> records = new ArrayList<TaggerFileRecord>();
    for (String piece : pieces) {
      records.add(createRecord(config, piece));
    }
    return records;
  }

  static public TaggerFileRecord createRecord(Properties config,
                                              String description) {
    // TODO: this prevents you from using the full power of
    // treeRanges, although perhaps that isn't used anywhere anyway
    String[] pieces = description.split(",");
    if (pieces.length == 1) {
      return new TaggerFileRecord(description, Format.TEXT,
                                  getEncoding(config),
                                  getTagSeparator(config),
                                  null, null, null);
    }

    String[] args = new String[pieces.length - 1];
    System.arraycopy(pieces, 0, args, 0, pieces.length - 1);
    String file = pieces[pieces.length - 1];
    Format format = Format.TEXT;
    String encoding = getEncoding(config);
    String tagSeparator = getTagSeparator(config);
    TreeTransformer treeTransformer = null;
    TreeNormalizer treeNormalizer = null;
    NumberRangesFileFilter treeRange = null;

    for (String arg : args) {
      String[] argPieces = arg.split("=", 2);
      if (argPieces.length != 2) {
        throw new IllegalArgumentException("TaggerFileRecord argument " + arg +
                                           " has an unexpected number of =s");
      }
      if (argPieces[0].equalsIgnoreCase(FORMAT)) {
        format = Format.valueOf(argPieces[1]);
      } else if (argPieces[0].equalsIgnoreCase(ENCODING)) {
        encoding = argPieces[1];
      } else if (argPieces[0].equalsIgnoreCase(TAG_SEPARATOR)) {
        tagSeparator = argPieces[1];
      } else if (argPieces[0].equalsIgnoreCase(TREE_TRANSFORMER)) {
        treeTransformer = ReflectionLoading.loadByReflection(argPieces[1]);
      } else if (argPieces[0].equalsIgnoreCase(TREE_NORMALIZER)) {
        treeNormalizer = ReflectionLoading.loadByReflection(argPieces[1]);
      } else if (argPieces[0].equalsIgnoreCase(TREE_RANGE)) {
        treeRange = new NumberRangesFileFilter(argPieces[1], true);
      } else {
        throw new IllegalArgumentException("TaggerFileRecord argument " + 
                                           argPieces[0] + " is unknown");
      }
    }
    return new TaggerFileRecord(file, format, encoding, tagSeparator,
                                treeTransformer, treeNormalizer, treeRange);
  }

  static public String getEncoding(Properties config) {
    String encoding = config.getProperty(TaggerConfig.ENCODING_PROPERTY);
    if (encoding == null)
      return TaggerConfig.ENCODING;
    return encoding;
  }

  static public String getTagSeparator(Properties config) {
    String tagSeparator = 
      config.getProperty(TaggerConfig.TAG_SEPARATOR_PROPERTY);
    if (tagSeparator == null)
      return TaggerConfig.TAG_SEPARATOR;
    return tagSeparator;
  }
}
