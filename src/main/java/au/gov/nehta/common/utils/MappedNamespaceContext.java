/*
 * Copyright 2009 NEHTA
 *
 * Licensed under the NEHTA Open Source (Apache) License; you may not use this
 * file except in compliance with the License. A copy of the License is in the
 * 'LICENSE.txt' file, which should be provided with this work.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package au.gov.nehta.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;

/**
 * Package-internal only to be used by {@link XPathUtils}.
 */
class MappedNamespaceContext implements NamespaceContext {

    private final Map<String, String> nsMap;

    MappedNamespaceContext(Map<String, String> nsMap) {
        this.nsMap = nsMap;
    }

    /**
     * @see NamespaceContext#getNamespaceURI(String)
     */
    @Override
    public String getNamespaceURI(String prefix) {
        return this.nsMap.get(prefix);
    }

    /**
     * @see NamespaceContext#getPrefix(String)
     */
    @Override
    public String getPrefix(String namespaceURI) {
        String prefix = null;
        for (String currPrefix : this.nsMap.keySet()) {
            String currNsUri = this.nsMap.get(currPrefix);
            if (currNsUri.equals(namespaceURI)) {
                prefix = currPrefix;
                break;
            }
        }
        return prefix;
    }

    /**
     * @see NamespaceContext#getPrefixes(String)
     */
    @Override
    public Iterator<String> getPrefixes(String namespaceURI) {
        List<String> prefixList = new ArrayList<>();
        String prefix = getPrefix(namespaceURI);
        if (prefix != null) {
            prefixList.add(prefix);
        }
        return prefixList.iterator();
    }
}
