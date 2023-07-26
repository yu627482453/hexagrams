package cn.bipher.hexagrams.common.utils.util.tree;

import java.util.List;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public interface TreeNode<I> {

	/**
	 * 获取节点 key
	 * @return 树节点 key
	 */
	I getKey();

	/**
	 * 获取该节点的父节点 key
	 * @return 父节点 key
	 */
	I getParentKey();

	/**
	 * 设置节点的子节点列表
	 * @param children 子节点
	 */
	<T extends TreeNode<I>> void setChildren(List<T> children);

	/**
	 * 获取所有子节点
	 * @return 子节点列表
	 */
	<T extends TreeNode<I>> List<T> getChildren();

}
