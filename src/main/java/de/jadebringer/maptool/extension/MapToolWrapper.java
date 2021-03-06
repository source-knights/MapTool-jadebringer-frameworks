/*
 * This software is copyright by the Jadebringer.de development team, and
 * licensed under the Affero GPL Version 3 or, at your option, any later
 * version.
 *
 * MapTool-jadebringer-extension Source Code is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the GNU Affero General Public
 * License * along with this source Code.  If not, please visit
 * <http://www.gnu.org/licenses/> and specifically the Affero license
 * text at <http://www.gnu.org/licenses/agpl.html>.
 */
package de.jadebringer.maptool.extension;

import de.jadebringer.maptool.extension.hook.ExtensionFunctions;
import java.util.List;
import net.rptools.maptool.client.MapTool;
import net.rptools.parser.function.Function;

public class MapToolWrapper {

  public static void main(String[] args) {
    ExtensionFunctions extensionFunctions = ExtensionFunctions.getInstance();
    List<Function> macroFunctions = MapTool.getParser().getMacroFunctions();
    if (!macroFunctions.contains(extensionFunctions)) {
      MapTool.getParser().getMacroFunctions().add(extensionFunctions);
    }
    MapTool.main(args);
  }
}
