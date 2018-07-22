/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package java.util.function;

import java.util.Objects;

/**
 * Represents a function that accepts one argument and produces a result.
 * 代表一个接受一个参数并产生一个结果的函数。
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object)}.
 * 这是一个函数接口，它的函数方法是apply(Object)。
 * @param <T> the type of the input to the function 函数的输入类型
 * @param <R> the type of the result of the function 函数的结果类型
 *
 * @since 1.8
 */
@FunctionalInterface
public interface Function<T, R> {

    /**
     * Applies this function to the given argument.
     * 把这个函数应用到给定的参数中。
     * @param t the function argument 函数参数
     * @return the function result 函数结果
     */
    R apply(T t);

    // 这里还有一个default方法，之前都没有注意，查了一下是Java8新特性，接口里的
    // default方法提供默认的方法实现

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     * 返回一个组合函数，它首先将before函数应用到输入，然后将this函数应用到
     * before应用的结果。
     * 如果在计算任意一个函数的时候抛出异常，它会被传递给该组合函数的调用者。
     * @param <V> the type of input to the {@code before} function, and to the
     *           composed function 
     *            before函数和组合函数的输入类型
     * @param before the function to apply before this function is applied
     *               在this函数应用之前应用的函数
     * @return a composed function that first applies the {@code before}
     * function and then applies this function
     *         首先应用before函数，然后应用this函数的组合函数
     * @throws NullPointerException if before is null 如果before是空
     *
     * @see #andThen(Function)
     */
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     * 返回一个组合函数，它先调用this函数，再调用after函数。如果在任一函数执行
     * 过程中抛出异常，异常将返回给组合函数的调用者。
     * @param <V> the type of output of the {@code after} function, and of the
     *           composed function
     *            after函数和组合函数的输出类型
     * @param after the function to apply after this function is applied
     *              在this函数应用之后应用的函数
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     *         首先应用this函数然后应用after函数的组合函数
     * @throws NullPointerException if after is null 如果after是空
     *
     * @see #compose(Function)
     */
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * Returns a function that always returns its input argument.
     * 返回一个总是返回它输入参数的函数
     * @param <T> the type of the input and output objects to the function
     *            函数的输入对象和输出对象的类型
     * @return a function that always returns its input argument
     *         总是返回它的输入参数的一个函数
     */
    static <T> Function<T, T> identity() {
        return t -> t;
    }

    /*
    * 一开始不知道compose和andThen方法T和R是怎么来的，然后在想为什么compose知道
    * before函数应用的结果类型一定符合this函数的输入类型呢？为什么andThen知道
    * after函数的输入类型一定符合this函数的结果类型呢？很明显，我漏看了接口声明，
    * 一开始就声明了这个两个参数，所以compose里before函数结果类型是? extends T，
    * 即before函数的结果类型是this函数的输入类型的子类，andThen里after函数输入
    * 类型是? super R，即after函数的输入类型是this函数的结果类型的超类，ok！
    */
}
