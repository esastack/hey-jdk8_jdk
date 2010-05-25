/*
 * Copyright (c) 2000, 2005, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package sun.applet.resources;

import java.util.ListResourceBundle;

public class MsgAppletViewer_zh_TW extends ListResourceBundle {

    public Object[][] getContents() {
        return new Object[][] {
            {"textframe.button.dismiss", "\u95dc\u9589"},
            {"appletviewer.tool.title", "Applet \u6aa2\u8996\u5668\uff1a{0}"},
            {"appletviewer.menu.applet", "Applet"},
            {"appletviewer.menuitem.restart", "\u91cd\u65b0\u555f\u52d5"},
            {"appletviewer.menuitem.reload", "\u91cd\u65b0\u8f09\u5165"},
            {"appletviewer.menuitem.stop", "\u505c\u6b62"},
            {"appletviewer.menuitem.save", "\u5132\u5b58..."},
            {"appletviewer.menuitem.start", "\u555f\u52d5"},
            {"appletviewer.menuitem.clone", "\u8907\u88fd..."},
            {"appletviewer.menuitem.tag", "\u6a19\u7c64..."},
            {"appletviewer.menuitem.info", "\u8cc7\u8a0a..."},
            {"appletviewer.menuitem.edit", "\u7de8\u8f2f"},
            {"appletviewer.menuitem.encoding", "\u5b57\u5143\u7de8\u78bc"},
            {"appletviewer.menuitem.print", "\u5217\u5370..."},
            {"appletviewer.menuitem.props", "\u5c6c\u6027..."},
            {"appletviewer.menuitem.close", "\u95dc\u9589"},
            {"appletviewer.menuitem.quit", "\u7d50\u675f"},
            {"appletviewer.label.hello", "Hello..."},
            {"appletviewer.status.start", "\u555f\u52d5 applet..."},
            {"appletviewer.appletsave.filedialogtitle","\u5c07 Applet \u4e32\u5217\u5316\u81f3\u6a94\u6848\u4e2d"},
            {"appletviewer.appletsave.err1", "\u5c07 {0} \u4e32\u5217\u5316\u70ba {1}"},
            {"appletviewer.appletsave.err2", "\u5728 appletSave\uff1a{0} \u4e2d"},
            {"appletviewer.applettag", "\u986f\u793a\u7684\u6a19\u7c64"},
            {"appletviewer.applettag.textframe", "Applet HTML \u6a19\u7c64"},
            {"appletviewer.appletinfo.applet", "-- \u6c92\u6709 applet \u8cc7\u8a0a --"},
            {"appletviewer.appletinfo.param", "-- \u6c92\u6709\u53c3\u6578\u8cc7\u8a0a --"},
            {"appletviewer.appletinfo.textframe", "Applet \u8cc7\u8a0a"},
            {"appletviewer.appletprint.fail", "\u5217\u5370\u5931\u6557\u3002"},
            {"appletviewer.appletprint.finish", "\u7d50\u675f\u5217\u5370\u3002"},
            {"appletviewer.appletprint.cancel", "\u53d6\u6d88\u5217\u5370\u3002"},
            {"appletviewer.appletencoding", "\u5b57\u5143\u7de8\u78bc\uff1a{0}"},
            {"appletviewer.parse.warning.requiresname", "\u8b66\u544a\uff1a <param name=... value=...> \u6a19\u7c64\u9700\u8981\u540d\u7a31\u5c6c\u6027\u3002"},
            {"appletviewer.parse.warning.paramoutside", "\u8b66\u544a\uff1a<param> \u6a19\u7c64\u5728 <applet> ... </applet>\u4e4b\u5916\u3002"},
            {"appletviewer.parse.warning.applet.requirescode", "\u8b66\u544a\uff1a<applet> \u6a19\u7c64\u9700\u8981\u7a0b\u5f0f\u78bc\u5c6c\u6027"},
            {"appletviewer.parse.warning.applet.requiresheight", "\u8b66\u544a\uff1a<applet> \u6a19\u7c64\u9700\u8981\u9ad8\u5ea6\u5c6c\u6027\u3002"},
            {"appletviewer.parse.warning.applet.requireswidth", "\u8b66\u544a\uff1a<applet> \u6a19\u7c64\u9700\u8981\u5bec\u5ea6\u5c6c\u6027\u3002"},
            {"appletviewer.parse.warning.object.requirescode", "\u8b66\u544a\uff1a<object> \u6a19\u7c64\u9700\u8981\u7a0b\u5f0f\u78bc\u5c6c\u6027\u3002"},
            {"appletviewer.parse.warning.object.requiresheight", "\u8b66\u544a\uff1a<object> \u6a19\u7c64\u9700\u8981\u9ad8\u5ea6\u5c6c\u6027\u3002"},
            {"appletviewer.parse.warning.object.requireswidth", "\u8b66\u544a\uff1a<object> \u6a19\u7c64\u9700\u8981\u5bec\u5ea6\u5c6c\u6027\u3002"},
            {"appletviewer.parse.warning.embed.requirescode", "\u8b66\u544a\uff1a<embed> \u6a19\u7c64\u9700\u8981\u7a0b\u5f0f\u78bc\u5c6c\u6027\u3002"},
            {"appletviewer.parse.warning.embed.requiresheight", "\u8b66\u544a\uff1a<embed> \u6a19\u7c64\u9700\u8981\u9ad8\u5ea6\u5c6c\u6027\u3002"},
            {"appletviewer.parse.warning.embed.requireswidth", "\u8b66\u544a\uff1a<embed> \u6a19\u7c64\u9700\u8981\u5bec\u5ea6\u5c6c\u6027"},
            {"appletviewer.parse.warning.appnotLongersupported", "\u8b66\u544a\uff1a\u4e0d\u518d\u652f\u63f4 <app> \u6a19\u7c64\uff0c\u8acb\u6539\u7528 <applet> \u3002"},
            {"appletviewer.usage", "\u4f7f\u7528\uff1aappletviewer <options> url(s)\n\n\u5176\u4e2d <options> \u5305\u542b:\n  -debug                  \u5728 JAVA \u9664\u932f\u4e2d\u555f\u52d5 applet \u6aa2\u8996\u5668\n  -encoding <encoding>    \u6307\u5b9a HTML \u6a94\u6848\u6240\u4f7f\u7528\u7684\u5b57\u5143\u7de8\u78bc\u65b9\u5f0f\n  -J<runtime flag>        \u5c07\u5f15\u6578\u50b3\u81f3 JAVA \u76f4\u8b6f\u7a0b\u5f0f\n\n -J \u9078\u9805\u4e26\u975e\u6a19\u6e96\u7684\uff0c\u800c\u4e14\u53d7\u6539\u8b8a\u63a7\u5236\u6642\u4e0d\u53e6\u884c\u901a\u77e5\u3002"},
            {"appletviewer.main.err.unsupportedopt", "\u4e0d\u652f\u63f4\u7684\u9078\u9805\uff1a{0}"},
            {"appletviewer.main.err.unrecognizedarg", "\u7121\u6cd5\u8fa8\u8b58\u7684\u5f15\u6578\uff1a{0}"},
            {"appletviewer.main.err.dupoption", "\u91cd\u8907\u4f7f\u7528\u9078\u9805\uff1a{0}"},
            {"appletviewer.main.err.inputfile", "\u672a\u6307\u5b9a\u8f38\u5165\u6a94\u6848\u3002"},
            {"appletviewer.main.err.badurl", "\u932f\u8aa4\u7684 URL: {0} ( {1} )"},
            {"appletviewer.main.err.io", "\u5728\u8b80\u53d6\u6642\u767c\u751f I/O \u7570\u5e38\uff1a{0}"},
            {"appletviewer.main.err.readablefile", "\u8acb\u78ba\u5b9a {0} \u662f\u4e00\u500b\u6a94\u6848\u800c\u4e14\u53ef\u8b80\u53d6\u3002"},
            {"appletviewer.main.err.correcturl", " {0} \u662f\u6b63\u78ba\u7684 URL \u55ce?"},
            {"appletviewer.main.prop.store", "\u7d66 AppletViewer \u7684\u4f7f\u7528\u8005\u6307\u5b9a\u5c6c\u6027"},
            {"appletviewer.main.err.prop.cantread", "\u7121\u6cd5\u8b80\u53d6\u4f7f\u7528\u8005\u5c6c\u6027\u6a94\u6848\uff1a{0}"},
            {"appletviewer.main.err.prop.cantsave", "\u7121\u6cd5\u5132\u5b58\u4f7f\u7528\u8005\u5c6c\u6027\u6a94\u6848\uff1a{0}"},
            {"appletviewer.main.warn.nosecmgr", "\u8b66\u544a\uff1a\u505c\u7528\u5b89\u5168\u6027\u529f\u80fd\u3002"},
            {"appletviewer.main.debug.cantfinddebug", "\u627e\u4e0d\u5230\u9664\u932f\u7a0b\u5f0f!"},
            {"appletviewer.main.debug.cantfindmain", "\u5728\u9664\u932f\u7a0b\u5f0f\u4e2d\u627e\u4e0d\u5230\u4e3b\u65b9\u6cd5!"},
            {"appletviewer.main.debug.exceptionindebug", "\u9664\u932f\u7a0b\u5f0f\u767c\u751f\u7570\u5e38"},
            {"appletviewer.main.debug.cantaccess", "\u7121\u6cd5\u5b58\u53d6\u9664\u932f\u7a0b\u5f0f!"},
            {"appletviewer.main.nosecmgr", "\u8b66\u544a\uff1a\u672a\u5b89\u88dd SecurityManager"},
            {"appletviewer.main.warning", "\u8b66\u544a\uff1a\u672a\u555f\u52d5 applet\u3002\u8acb\u78ba\u5b9a\u6240\u8f38\u5165\u7684\u5305\u542b <applet> \u6a19\u7c64\u3002"},
            {"appletviewer.main.warn.prop.overwrite", "\u8b66\u544a\uff1a\u5728\u4f7f\u7528\u8005\u8981\u6c42\u4e0b\u66ab\u6642\u8986\u5beb\u7cfb\u7d71\u5c6c\u6027: key: {0} \u820a\u503c\uff1a{1} \u65b0\u503c\uff1a{2}"},
            {"appletviewer.main.warn.cantreadprops", "\u8b66\u544a\uff1a\u7121\u6cd5\u8b80\u53d6 AppletViewer \u5c6c\u6027\u6a94\uff1a{0} \u4f7f\u7528\u9810\u8a2d\u503c\u3002"},
            {"appletioexception.loadclass.throw.interrupted", "\u985e\u5225\u8f09\u5165\u4e2d\u65b7\uff1a{0}"},
            {"appletioexception.loadclass.throw.notloaded", "\u985e\u5225\u672a\u88ab\u8f09\u5165\uff1a{0}"},
            {"appletclassloader.loadcode.verbose", "\u958b\u555f\u4e32\u6d41\u81f3\uff1a{0} \u4ee5\u7372\u5f97\uff1a{1}"},
            {"appletclassloader.filenotfound", "\u5728\u5c0b\u627e\uff1a{0} \u6642\u672a\u627e\u5230\u6a94\u6848"},
            {"appletclassloader.fileformat", "\u5728\u8f09\u5165\uff1a{0} \u6642\u767c\u751f\u6a94\u6848\u683c\u5f0f\u7570\u5e38"},
            {"appletclassloader.fileioexception", "\u5728\u8f09\u5165\uff1a{0} \u6642\u767c\u751f I/O \u7570\u5e38"},
            {"appletclassloader.fileexception", "\u5728\u8f09\u5165\uff1a{1} \u6642\u767c\u751f {0} \u7570\u5e38"},
            {"appletclassloader.filedeath", "\u5728\u8f09\u5165 {1} \u6642 {0} \u88ab\u522a\u9664"},
            {"appletclassloader.fileerror", "\u5728\u8f09\u5165 {1} \u6642\u767c\u751f {0} \u932f\u8aa4"},
            {"appletclassloader.findclass.verbose.findclass", "{0} \u767c\u73fe\u985e\u5225 {1}"},
            {"appletclassloader.findclass.verbose.openstream", "\u958b\u555f\u8cc7\u6599\u6d41\u81f3\uff1a{0} \u4ee5\u7372\u5f97 {1}"},
            {"appletclassloader.getresource.verbose.forname", "AppletClassLoader.getResource \u540d\u7a31\uff1a{0}"},
            {"appletclassloader.getresource.verbose.found", "\u767c\u73fe\u8cc7\u6e90\uff1a {0} \u662f\u7cfb\u7d71\u8cc7\u6e90"},
            {"appletclassloader.getresourceasstream.verbose", "\u88ab\u767c\u73fe\u7684\u8cc7\u6e90\uff1a{0} \u662f\u7cfb\u7d71\u8cc7\u6e90"},
            {"appletpanel.runloader.err", "\u7269\u4ef6\u6216\u662f\u7a0b\u5f0f\u78bc\u53c3\u6578!"},
            {"appletpanel.runloader.exception", "\u53cd\u4e32\u5217\u5316 {0} \u6642\u767c\u751f\u7570\u5e38"},
            {"appletpanel.destroyed", "Applet \u5df2\u92b7\u6bc0"},
            {"appletpanel.loaded", "Applet \u5df2\u8f09\u5165\u3002"},
            {"appletpanel.started", "Applet \u5df2\u555f\u52d5\u3002"},
            {"appletpanel.inited", "Applet \u5df2\u521d\u59cb\u5316\u3002"},
            {"appletpanel.stopped", "Applet \u5df2\u505c\u6b62\u3002"},
            {"appletpanel.disposed", "Applet \u5df2\u68c4\u7f6e\u3002"},
            {"appletpanel.nocode", "APPLET \u6a19\u7c64\u907a\u6f0f CODE \u53c3\u6578\u3002"},
            {"appletpanel.notfound", "\u8f09\u5165\ufe30\u627e\u4e0d\u5230\u985e\u5225 {0}\u3002"},
            {"appletpanel.nocreate", "\u8f09\u5165\ufe30\u7121\u6cd5\u5275\u8a2d {0}\u3002"},
            {"appletpanel.noconstruct", "\u8f09\u5165\ufe30{0} \u4e0d\u662f\u516c\u7528\u6216\u6c92\u6709\u516c\u7528\u5efa\u69cb\u5143\u3002"},
            {"appletpanel.death", "\u5df2\u522a\u9664"},
            {"appletpanel.exception", "\u7570\u5e38\ufe30{0}\u3002"},
            {"appletpanel.exception2", "\u7570\u5e38\ufe30{0}\ufe30{1}\u3002"},
            {"appletpanel.error", "\u932f\u8aa4\ufe30{0}\u3002"},
            {"appletpanel.error2", "\u932f\u8aa4\ufe30{0}\ufe30{1}\u3002"},
            {"appletpanel.notloaded", "\u521d\u59cb\u5316\ufe30\u6c92\u6709\u8f09\u5165 applet\u3002"},
            {"appletpanel.notinited", "\u555f\u52d5\ufe30\u6c92\u6709\u521d\u59cb\u5316 applet\u3002"},
            {"appletpanel.notstarted", "\u505c\u6b62\ufe30\u6c92\u6709\u555f\u52d5 applet\u3002"},
            {"appletpanel.notstopped", "\u92b7\u6bc0\ufe30\u6c92\u6709\u505c\u6b62 applet\u3002"},
            {"appletpanel.notdestroyed", "\u68c4\u7f6e\ufe30\u6c92\u6709\u92b7\u6bc0 applet\u3002"},
            {"appletpanel.notdisposed", "\u8f09\u5165\ufe30\u6c92\u6709\u68c4\u7f6e applet\u3002"},
            {"appletpanel.bail", "\u4e2d\u65b7\ufe30\u91cb\u51fa\u3002"},
            {"appletpanel.filenotfound", "\u5c0b\u627e\ufe30{0} \u6642\u627e\u4e0d\u5230\u6a94\u6848"},
            {"appletpanel.fileformat", "\u8f09\u5165\ufe30{0} \u6642\u767c\u751f\u6a94\u6848\u683c\u5f0f\u7570\u5e38"},
            {"appletpanel.fileioexception", "\u8f09\u5165\ufe30{0} \u6642\u767c\u751f I/O \u7570\u5e38"},
            {"appletpanel.fileexception", "\u8f09\u5165\ufe30{1} \u6642\u767c\u751f {0} \u7570\u5e38"},
            {"appletpanel.filedeath", "\u8f09\u5165\ufe30{1} \u6642\u522a\u9664\u4e86 {0}"},
            {"appletpanel.fileerror", "\u8f09\u5165\ufe30{1} \u6642\u767c\u751f {0} \u932f\u8aa4"},
            {"appletpanel.badattribute.exception", "HTML \u5256\u6790\uff1a\u5bec\u5ea6/\u9ad8\u5ea6\u5c6c\u6027\u503c\u4e0d\u6b63\u78ba"},
            {"appletillegalargumentexception.objectinputstream", "AppletObjectInputStream \u9700\u8981\u975e\u7a7a\u7684\u8f09\u5165\u5668"},
            {"appletprops.title", "AppletViewer \u5c6c\u6027"},
            {"appletprops.label.http.server", "Http \u4ee3\u7406\u4f3a\u670d\u5668\ufe30"},
            {"appletprops.label.http.proxy", "Http \u4ee3\u7406\u9023\u63a5\u57e0\ufe30"},
            {"appletprops.label.network", "\u7db2\u8def\u5b58\u53d6\ufe30"},
            {"appletprops.choice.network.item.none", "\u7121"},
            {"appletprops.choice.network.item.applethost", "Applet \u4e3b\u6a5f"},
            {"appletprops.choice.network.item.unrestricted", "\u7121\u9650\u5236\u7684"},
            {"appletprops.label.class", "\u985e\u5225\u5b58\u53d6\ufe30"},
            {"appletprops.choice.class.item.restricted", "\u53d7\u9650\u5236\u7684"},
            {"appletprops.choice.class.item.unrestricted", "\u7121\u9650\u5236\u7684"},
            {"appletprops.label.unsignedapplet", "\u5141\u8a31\u7121\u7c3d\u540d\u7684 applet\ufe30"},
            {"appletprops.choice.unsignedapplet.no", "\u5426"},
            {"appletprops.choice.unsignedapplet.yes", "\u662f"},
            {"appletprops.button.apply", "\u5957\u7528"},
            {"appletprops.button.cancel", "\u53d6\u6d88"},
            {"appletprops.button.reset", "\u91cd\u8a2d"},
            {"appletprops.apply.exception", "\u7121\u6cd5\u5132\u5b58\u5c6c\u6027\ufe30{0}"},
            /* 4066432 */
            {"appletprops.title.invalidproxy", "\u8f38\u5165\u9805\u76ee\u7121\u6548"},
            {"appletprops.label.invalidproxy", "\u4ee3\u7406\u9023\u63a5\u57e0\u5fc5\u9808\u662f\u6b63\u6574\u6578\u503c\u3002"},
            {"appletprops.button.ok", "\u78ba\u5b9a"},
            /* end 4066432 */
            {"appletprops.prop.store", "\u7528\u65bc AppletViewer \u7684\u4f7f\u7528\u8005\u6307\u5b9a\u5c6c\u6027"},
            {"appletsecurityexception.checkcreateclassloader", "\u5b89\u5168\u6027\u7570\u5e38\uff1a\u985e\u5225\u8f09\u5165\u5668"},
            {"appletsecurityexception.checkaccess.thread", "\u5b89\u5168\u6027\u7570\u5e38\ufe30\u57f7\u884c\u7dd2"},
            {"appletsecurityexception.checkaccess.threadgroup", "\u5b89\u5168\u6027\u7570\u5e38\ufe30\u57f7\u884c\u7dd2\u7fa4\u7d44\ufe30{0}"},
            {"appletsecurityexception.checkexit", "\u5b89\u5168\u6027\u7570\u5e38\ufe30\u7d50\u675f\ufe30{0}"},
            {"appletsecurityexception.checkexec", "\u5b89\u5168\u6027\u7570\u5e38\ufe30\u57f7\u884c\ufe30{0}"},
            {"appletsecurityexception.checklink", "\u5b89\u5168\u6027\u7570\u5e38\ufe30\u9023\u7d50\ufe30{0}"},
            {"appletsecurityexception.checkpropsaccess", "\u5b89\u5168\u6027\u7570\u5e38\ufe30\u5c6c\u6027"},
            {"appletsecurityexception.checkpropsaccess.key", "\u5b89\u5168\u6027\u7570\u5e38\ufe30\u5c6c\u6027\u5b58\u53d6 {0}"},
            {"appletsecurityexception.checkread.exception1", "\u5b89\u5168\u6027\u7570\u5e38\ufe30{0}, {1}"},
            {"appletsecurityexception.checkread.exception2", "\u5b89\u5168\u6027\u7570\u5e38\ufe30file.read: {0}"},
            {"appletsecurityexception.checkread", "file.write: {0} == {1}"},
            {"appletsecurityexception.checkwrite.exception", "\u5b89\u5168\u6027\u7570\u5e38\ufe30{0}, {1}"},
            {"appletsecurityexception.checkwrite", "\u5b89\u5168\u6027\u7570\u5e38\ufe30{0} == {1}"},
            {"appletsecurityexception.checkread.fd", "\u5b89\u5168\u6027\u7570\u5e38\ufe30 fd.read"},
            {"appletsecurityexception.checkwrite.fd", "\u5b89\u5168\u6027\u7570\u5e38\ufe30 fd.write"},
            {"appletsecurityexception.checklisten", "\u5b89\u5168\u6027\u7570\u5e38\ufe30 socket.listen: {0}"},
            {"appletsecurityexception.checkaccept", "\u5b89\u5168\u6027\u7570\u5e38\ufe30 socket.accept: {0}:{1}"},
            {"appletsecurityexception.checkconnect.networknone", "\u5b89\u5168\u6027\u7570\u5e38\ufe30{0}->{1}"},
            {"appletsecurityexception.checkconnect.networkhost1", "\u5b89\u5168\u6027\u7570\u5e38\ufe30 \u7121\u6cd5\u5f9e {1} \u9023\u63a5\u5230\u539f\u59cb\u7684 {0}\u3002"},
            {"appletsecurityexception.checkconnect.networkhost2", "\u5b89\u5168\u6027\u7570\u5e38\ufe30\u7121\u6cd5\u89e3\u8b6f\u4e3b\u6a5f {0} \u6216 {1} \u7684 IP\u3002"},
            {"appletsecurityexception.checkconnect.networkhost3", "\u5168\u6027\u7570\u5e38\ufe30\u7121\u6cd5\u89e3\u8b6f\u4e3b\u6a5f {0} \u7684 IP\u3002\u8acb\u53c3\u95b1 trustProxy \u5c6c\u6027\u3002"},
            {"appletsecurityexception.checkconnect", "\u5b89\u5168\u6027\u7570\u5e38\ufe30\u9023\u63a5\ufe30{0}->{1}"},
            {"appletsecurityexception.checkpackageaccess", "\u5b89\u5168\u6027\u7570\u5e38\ufe30\u7121\u6cd5\u5b58\u53d6\u5957\u88dd\u8edf\u9ad4\ufe30{0}"},
            {"appletsecurityexception.checkpackagedefinition", "\u5b89\u5168\u6027\u7570\u5e38\ufe30\u7121\u6cd5\u5b9a\u7fa9\u5957\u88dd\u8edf\u9ad4\ufe30{0}"},
            {"appletsecurityexception.cannotsetfactory", "\u5b89\u5168\u6027\u7570\u5e38\ufe30\u7121\u6cd5\u8a2d\u5b9a factory"},
            {"appletsecurityexception.checkmemberaccess", "\u5b89\u5168\u6027\u7570\u5e38\ufe30\u6aa2\u67e5\u6210\u54e1\u5b58\u53d6\u6b0a\u9650"},
            {"appletsecurityexception.checkgetprintjob", "\u5b89\u5168\u6027\u7570\u5e38\ufe30getPrintJob"},
            {"appletsecurityexception.checksystemclipboardaccess", "\u5b89\u5168\u6027\u7570\u5e38\ufe30getSystemClipboard"},
            {"appletsecurityexception.checkawteventqueueaccess", "\u5b89\u5168\u6027\u7570\u5e38\ufe30getEventQueue"},
            {"appletsecurityexception.checksecurityaccess", "\u5b89\u5168\u6027\u7570\u5e38\ufe30\u5b89\u5168\u6027\u64cd\u4f5c\ufe30{0}"},
            {"appletsecurityexception.getsecuritycontext.unknown", "\u672a\u77e5\u7684\u985e\u5225\u8f09\u5165\u5668\u985e\u578b\u3002\u7121\u6cd5\u6aa2\u67e5 getContext"},
            {"appletsecurityexception.checkread.unknown", "\u672a\u77e5\u7684\u985e\u5225\u8f09\u5165\u5668\u985e\u578b\u3002\u7121\u6cd5\u67e5\u770b\u6aa2\u67e5\u8b80\u53d6 {0}"},
            {"appletsecurityexception.checkconnect.unknown", "\u672a\u77e5\u7684\u985e\u5225\u8f09\u5165\u5668\u985e\u578b\u3002\u7121\u6cd5\u67e5\u770b\u6aa2\u67e5\u9023\u63a5"},
        };
    }
}
