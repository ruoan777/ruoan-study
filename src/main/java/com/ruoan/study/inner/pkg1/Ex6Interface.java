package com.ruoan.study.inner.pkg1;

// innerclasses/Ex6.java
// TIJ4 Chapter Innerclasses, Exercise 6, page 353
/* Create an interface with at least one method, in its own package. Create
 * a class in a separate package. Add a protected com.ruoan.study.inner class that
 * implements the interface. In a third package, inherit from your class and
 * inside a method, return an object of the protected com.ruoan.study.inner class, upcasting
 * to the interface during the return.
 */

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年06月27日 11:42:00
 */
public interface Ex6Interface {
    String say();
}




