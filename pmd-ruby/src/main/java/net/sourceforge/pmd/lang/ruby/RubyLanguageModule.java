/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ruby;

import net.sourceforge.pmd.cpd.AnyTokenizer;
import net.sourceforge.pmd.cpd.Tokenizer;
import net.sourceforge.pmd.lang.LanguagePropertyBundle;
import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.impl.CpdOnlyLanguageModuleBase;

/**
 * Language implementation for Ruby.
 *
 * @author Zev Blut zb@ubit.com
 */
public class RubyLanguageModule extends CpdOnlyLanguageModuleBase {
    private static final String ID = "ruby";

    public RubyLanguageModule() {
        super(LanguageMetadata.withId(ID).name("Ruby").extensions("rb", "cgi", "class"));
    }

    public static RubyLanguageModule getInstance() {
        return (RubyLanguageModule) LanguageRegistry.CPD.getLanguageById(ID);
    }

    @Override
    public Tokenizer createCpdTokenizer(LanguagePropertyBundle bundle) {
        return new AnyTokenizer("#");
    }
}
