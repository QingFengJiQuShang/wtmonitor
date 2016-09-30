package smart.sys.platform.springUtils;

import org.springframework.context.ApplicationContext;

public class ContextHolder {
	private static final ContextHolder instance = new ContextHolder();
	  private ApplicationContext ac;

	  public static ContextHolder getInstance()
	  {
	    return instance;
	  }

	  public synchronized void setApplicationContext(ApplicationContext ac) {
	    this.ac = ac;
	  }

	  public ApplicationContext getApplicationContext() {
	    return this.ac;
	  }
}
