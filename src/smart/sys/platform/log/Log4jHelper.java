package smart.sys.platform.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Log4jHelper
{
  private Log log; 

  public Log4jHelper(Class<? extends Object> className)
  {
    this.log = LogFactory.getLog(className);
  }

  public Log4jHelper(String loggerName)
  {
    this.log = LogFactory.getLog(loggerName);
  }

  public Log exceptionLog()
  {
    return this.log;
  }

  public void debug(Object arg0) {
    this.log.debug(arg0);
  }

  public void debug(Object arg0, Throwable arg1) {
    this.log.debug(arg0, arg1);
  }

  public void info(Object arg0) {
    this.log.info(arg0);
  }

  public void info(Object arg0, Throwable arg1) {
    this.log.info(arg0, arg1);
  }

  public void warn(Object arg0) {
    this.log.warn(arg0);
  }

  public void warn(Object arg0, Throwable arg1) {
    this.log.warn(arg0, arg1);
  }

  public void error(Object arg0) {
    this.log.error(arg0);
  }

  public void error(Object arg0, Throwable arg1) {
    this.log.error(arg0, arg1);
  }

  public void fatal(Object arg0) {
    this.log.fatal(arg0);
  }

  public void fatal(Object arg0, Throwable arg1) {
    this.log.fatal(arg0, arg1);
  }
}