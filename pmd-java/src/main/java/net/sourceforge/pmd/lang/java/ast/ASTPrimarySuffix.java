/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
/* Generated By:JJTree: Do not edit this line. ASTPrimarySuffix.java */

package net.sourceforge.pmd.lang.java.ast;

public class ASTPrimarySuffix extends AbstractJavaTypeNode {

    private boolean isArguments;
    private boolean isArrayDereference;

    public ASTPrimarySuffix(int id) {
        super(id);
    }

    public ASTPrimarySuffix(JavaParser p, int id) {
        super(p, id);
    }

    public void setIsArrayDereference() {
        isArrayDereference = true;
    }

    public boolean isArrayDereference() {
        return isArrayDereference;
    }

    public void setIsArguments() {
        this.isArguments = true;
    }

    public boolean isArguments() {
        return this.isArguments;
    }

    /**
     * Get the number of arguments for this primary suffix. One should call
     * {@link #isArguments()} to see if there are arguments. If this method is
     * called when there are no arguments it returns <code>-1</code>.
     * 
     * @return A non-negative argument number when there are arguments,
     *         <code>-1</code> otherwise.
     */
    public int getArgumentCount() {
        if (!this.isArguments()) {
            return -1;
        }
        return ((ASTArguments) jjtGetChild(jjtGetNumChildren() - 1)).getArgumentCount();
    }

    /**
     * Accept the visitor. *
     */
    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}
