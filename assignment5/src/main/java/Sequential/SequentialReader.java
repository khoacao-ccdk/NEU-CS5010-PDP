package Sequential;

import Common.Reader;
import Common.StudentClickInformation;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * SequentialReader is used to read input files
 *
 * @author Cody cao, Letian Shi
 */
public class SequentialReader<T> implements Reader {

  private String filepath;
  private List<T> data;
  private Iterator<T> iter;
  private CsvToBean<T> parser;
  private Class beanClass;

  /**
   * @param filepath  filepath to input files
   * @param beanClass beanClass type
   */
  public SequentialReader(String filepath, Class beanClass) {
    this.filepath = filepath;
    this.beanClass = beanClass;
    data=new ArrayList<>();
  }

  /**
   *
   * @throws Exception if file not exist
   */
  public void setUpParser() throws Exception {
    Path path;
    try {
      path = new File(filepath).toPath();
    } catch (Exception e) {
      throw new Exception(ERROR_READING_FILE);
    }
    try (
        java.io.Reader reader = Files.newBufferedReader(path);
    ) {
      parser = new CsvToBeanBuilder(reader)
          .withType(beanClass)
          .withSkipLines(NUM_LINE_TO_SKIP)
          .withIgnoreLeadingWhiteSpace(true)
          .build();
      data=parser.parse();
      iter=data.iterator();
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception(ERROR_READING_FILE);
    }
  }

  /**
   *
   * @return filepath
   */
  public String getFilepath() {
    return filepath;
  }

  /**
   *
   * @return bean class
   */
  public Class getBeanClass() {
    return beanClass;
  }

  /**
   *
   * @return data
   */
  public List<T> getData() {
    return data;
  }

  /**
   *
   * @return parser from reading file
   */
  public CsvToBean<T> getParser() {
    return parser;
  }

  /**
   *
   * @return iterator from reading file
   */
  public Iterator<T> getIter() {
    return iter;
  }

  /**
   *
   * @return iterator next line
   */
  @Override
  public Object nextLine() {
    return iter.next();
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
    if (!(o instanceof SequentialReader<?> that)) {
      return false;
    }
    return Objects.equals(getFilepath(), that.getFilepath()) && Objects.equals(
        getData(), that.getData()) && Objects.equals(getIter(), that.getIter())
        && Objects.equals(getParser(), that.getParser()) && Objects.equals(
        getBeanClass(), that.getBeanClass());
  }
  /**
   *
   * @return hashcode of the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(getFilepath(), getData(), getIter(), getParser(), getBeanClass());
  }
  /**
   *
   * @return object to string
   */
  @Override
  public String toString() {
    return "SequentialReader{" +
        "filepath='" + filepath + '\'' +
        ", data=" + data +
        ", iter=" + iter +
        ", parser=" + parser +
        ", beanClass=" + beanClass +
        '}';
  }
}
