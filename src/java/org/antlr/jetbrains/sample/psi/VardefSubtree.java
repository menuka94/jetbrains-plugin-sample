package org.antlr.jetbrains.sample.psi;

import com.intellij.lang.ASTNode;
import org.antlr.jetbrains.adaptor.psi.IdentifierDefSubtree;
import org.jetbrains.annotations.NotNull;

public class VardefSubtree extends IdentifierDefSubtree {
	public VardefSubtree(@NotNull ASTNode node) {
		// TODO: replace second parameter with a real value
		super(node, null);
	}
}
