/*
 * This software Copyright by the RPTools.net development team, and licensed under the Affero GPL Version 3 or, at your option, any later version.
 *
 * MapTool Source Code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the GNU Affero General Public License * along with this source Code. If not, please visit <http://www.gnu.org/licenses/> and specifically the Affero license text
 * at <http://www.gnu.org/licenses/agpl.html>.
 */
package de.jadebringer.maptool.frameworks.jadebringer.functions;

import java.math.BigDecimal;
import java.util.List;

import de.jadebringer.maptool.extensionframework.ExtensionFunction;
import de.jadebringer.maptool.extensionframework.FunctionCaller;
import de.jadebringer.maptool.frameworks.base.functions.SettingsFunctions;
import net.rptools.maptool.client.MapTool;
import net.rptools.maptool.language.I18N;
import net.rptools.parser.Parser;
import net.rptools.parser.ParserException;

/**
 * 
 * @author oliver.szymanski
 */
public class JadebringerSettingsFunctions extends ExtensionFunction {
	public static final String SET_GOD_MODE = "setGodMode";
	public static final String TOGGLE_GOD_MODE = "toggleGodMode";
	public static final String IS_GOD_MODE = "isGodMode";

	public JadebringerSettingsFunctions() {
		super( 
		    Alias.create(IS_GOD_MODE, 0, 0), 
        Alias.create(TOGGLE_GOD_MODE, 0, 0), 
		    Alias.create(SET_GOD_MODE, 1, 1));
		setTrustedRequired(true);
	}

	private final static JadebringerSettingsFunctions instance = new JadebringerSettingsFunctions();

	private final SettingsFunctions settingsFunctions = SettingsFunctions.getInstance();
  
	public static JadebringerSettingsFunctions getInstance() {
		return instance;
	}

	@Override
	public Object run(Parser parser, String functionName, List<Object> parameters) throws ParserException {

		if (!MapTool.getParser().isMacroTrusted()) {
			throw new ParserException(I18N.getText("macro.function.general.noPerm", functionName));
		}
		
		if (SET_GOD_MODE.equals(functionName)) {
			BigDecimal godModeEnabled = FunctionCaller.getParam(parameters, 0);
			return setGodMode(parser, godModeEnabled);
		} else if (IS_GOD_MODE.equals(functionName)) {
			return isGodMode(parser);
    } else if (TOGGLE_GOD_MODE.equals(functionName)) {
      setGodMode(parser, BigDecimal.ZERO.equals(isGodMode(parser)) ? BigDecimal.ONE : BigDecimal.ZERO);
      return isGodMode(parser);
    }
	
		throw new ParserException("non existing function: " + functionName);
	}

  private Object setGodMode(Parser parser, BigDecimal godModeEnabled)
      throws ParserException {
    settingsFunctions.setSetting(parser, "jadebringer.godmode.enabled", godModeEnabled, null);
    return "";
  }

  private Object isGodMode(Parser parser) throws ParserException {
    if (BigDecimal.ONE.compareTo((BigDecimal)settingsFunctions.getSetting(parser, "jadebringer.godmode.enabled", BigDecimal.ZERO, null)) <= 0) {
      return BigDecimal.ONE;
    } else {
      return BigDecimal.ZERO;
    }
  }

}
