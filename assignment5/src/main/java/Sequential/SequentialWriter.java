package Sequential;

import Common.Writer;
import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Objects;
/**
 * SequentialWriter is used to write output directory and files
 *
 * @author Cody cao, Letian Shi
 */
public class SequentialWriter implements Writer {

  private String filename;
  private String directory;
  private String[] data;
  private File outputFile;
  private final String OUTPUT_FOLDER_NAME = "SequentialOutput";

  /**
   *
   * @param directory directory of output files
   * @param filename filename of output files
   * @param data content of output files
   */
  public SequentialWriter(String directory, String filename, String[] data) {
    this.filename = filename;
    this.data = data;
    this.directory = directory;
  }

  /**
   *
   * @return filename
   */
  public String getFilename() {
    return filename;
  }

  /**
   *
   * @return data
   */
  public String[] getData() {
    return data;
  }

  /**
   *  create directory for output files
   * @param directory a String represents the path to file
   */
  @Override
  public void createFile(String directory) {
    File theDir = new File(OUTPUT_FOLDER_NAME);
    if (!theDir.exists()){
      theDir.mkdirs();
    }
    File outputFile = new File(OUTPUT_FOLDER_NAME,
        String.format(DEFAULT_OUTPUT_FILE_FORMAT, filename));
    this.outputFile=outputFile;
  }

  /**
   * write to files line by line
   * @param filename a String represents the path to file
   * @param data an Object represents the data needed to write to file, one row a time
   * @param append a Boolean telling writer to either append or overwrite
   * @throws Exception if file not exist
   */
  @Override
  public void writeToFile(String filename, String[] data, boolean append) throws Exception {
    createFile(directory);
    try {
      java.io.Writer writer = new BufferedWriter(
          new FileWriter(
              this.outputFile, append
          ));

      CSVWriter output = new CSVWriter(writer,
          CSVWriter.DEFAULT_SEPARATOR,
          CSVWriter.NO_QUOTE_CHARACTER,
          CSVWriter.DEFAULT_ESCAPE_CHARACTER,
          CSVWriter.DEFAULT_LINE_END);

      output.writeNext(data);
      output.close();
    } catch (Exception e) {
      throw new Exception(ERROR_CANNOT_WRITE);
    }
  }

  /**
   *
   * @param o object to be checked
   * @return true if the same, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SequentialWriter that)) {
      return false;
    }
    return Objects.equals(getFilename(), that.getFilename()) && Objects.equals(
        directory, that.directory) && Arrays.equals(getData(), that.getData())
        && Objects.equals(outputFile, that.outputFile) && Objects.equals(
        OUTPUT_FOLDER_NAME, that.OUTPUT_FOLDER_NAME);
  }

  /**
   *
   * @return hashcode of the object
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(getFilename(), directory, outputFile, OUTPUT_FOLDER_NAME);
    result = 31 * result + Arrays.hashCode(getData());
    return result;
  }

  /**
   *
   * @return object to string
   */
  @Override
  public String toString() {
    return "SequentialWriter{" +
        "filename='" + filename + '\'' +
        ", directory='" + directory + '\'' +
        ", data=" + Arrays.toString(data) +
        ", outputFile=" + outputFile +
        ", OUTPUT_FOLDER_NAME='" + OUTPUT_FOLDER_NAME + '\'' +
        '}';
  }
}
