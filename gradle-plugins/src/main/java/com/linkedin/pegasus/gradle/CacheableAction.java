package com.linkedin.pegasus.gradle;

import org.gradle.api.Action;


/**
 * An action that is build cache friendly because it has a fixed class name.
 * <p>
 * The delegate action this wraps is intended to be a lambda.
 * <p>
 * In Gradle versions less than 5.0, the cache key for an action is computed by
 * calling {@code getClass().getName()} on the action. If the action is a lambda,
 * this is unstable, such as com.linkedin.MyCustomPlugin$$Lambda$188/376495921.
 * The JVM is adding to the end of the autogenerated class for the lambda a hash
 * code value.
 * <p>
 * In Gradle 5.0, tasks with lambdas in {@code doFirst} and {@code doLast} blocks
 * will cause the task to no longer participate in up-to-date checks or caching
 * at all.
 *
 * @param <T> type with which the action executes
 */
public class CacheableAction<T> implements Action<T>
{
  private final Action<T> delegate;

  public CacheableAction(Action<T> delegate)
  {
    this.delegate = delegate;
  }

  @Override
  public void execute(T t)
  {
    delegate.execute(t);
  }
}