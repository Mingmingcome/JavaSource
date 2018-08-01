/*
 * Copyright (c) 2005, 2006, Oracle and/or its affiliates. All rights reserved.
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

package javax.lang.model.type;


/**
 * Represents an array type.
 * A multidimensional array type is represented as an array type
 * whose component type is also an array type.
 * 表示一个数组类型。
 * 一个多维数组类型被表示为一个数组类型，其组件类型也是一个数组类型。
 * 
 * @author Joseph D. Darcy
 * @author Scott Seligman
 * @author Peter von der Ah&eacute;
 * @since 1.6
 */
public interface ArrayType extends ReferenceType {

    /**
     * Returns the component type of this array type.
     * 返回这个数组类型的组件类型。（理解：如果是一维数组的话，
     * 返回这个数组的元素类型）
     * @return the component type of this array type
     *         这个数组类型的组件类型。
     */
    TypeMirror getComponentType();
}
