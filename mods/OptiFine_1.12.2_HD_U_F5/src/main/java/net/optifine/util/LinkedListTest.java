/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.optifine.render.VboRange;
import net.optifine.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) throws Exception {
        VboRange range;
        int i;
        LinkedList<VboRange> linkedList = new LinkedList<VboRange>();
        ArrayList<VboRange> listFree = new ArrayList<VboRange>();
        ArrayList<VboRange> listUsed = new ArrayList<VboRange>();
        Random random = new Random();
        int count = 100;
        for (i = 0; i < count; ++i) {
            range = new VboRange();
            range.setPosition(i);
            listFree.add(range);
        }
        for (i = 0; i < 100000; ++i) {
            LinkedList.Node<VboRange> node;
            LinkedListTest.checkLists(listFree, listUsed, count);
            LinkedListTest.checkLinkedList(linkedList, listUsed.size());
            if (i % 5 == 0) {
                LinkedListTest.dbgLinkedList(linkedList);
            }
            if (random.nextBoolean()) {
                if (listFree.isEmpty()) continue;
                range = (VboRange)listFree.get(random.nextInt(listFree.size()));
                node = range.getNode();
                if (random.nextBoolean()) {
                    linkedList.addFirst(node);
                    LinkedListTest.dbg("Add first: " + range.getPosition());
                } else if (random.nextBoolean()) {
                    linkedList.addLast(node);
                    LinkedListTest.dbg("Add last: " + range.getPosition());
                } else {
                    if (listUsed.isEmpty()) continue;
                    VboRange rangePrev = (VboRange)listUsed.get(random.nextInt(listUsed.size()));
                    LinkedList.Node<VboRange> nodePrev = rangePrev.getNode();
                    linkedList.addAfter(nodePrev, node);
                    LinkedListTest.dbg("Add after: " + rangePrev.getPosition() + ", " + range.getPosition());
                }
                listFree.remove(range);
                listUsed.add(range);
                continue;
            }
            if (listUsed.isEmpty()) continue;
            range = (VboRange)listUsed.get(random.nextInt(listUsed.size()));
            node = range.getNode();
            linkedList.remove(node);
            LinkedListTest.dbg("Remove: " + range.getPosition());
            listUsed.remove(range);
            listFree.add(range);
        }
    }

    private static void dbgLinkedList(LinkedList<VboRange> linkedList) {
        StringBuffer sb = new StringBuffer();
        Iterator<LinkedList.Node<VboRange>> it = linkedList.iterator();
        while (it.hasNext()) {
            LinkedList.Node<VboRange> node = it.next();
            VboRange range = node.getItem();
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(range.getPosition());
        }
        LinkedListTest.dbg("List: " + sb);
    }

    private static void checkLinkedList(LinkedList<VboRange> linkedList, int used) {
        if (linkedList.getSize() != used) {
            throw new RuntimeException("Wrong size, linked: " + linkedList.getSize() + ", used: " + used);
        }
        int count = 0;
        for (LinkedList.Node<VboRange> node = linkedList.getFirst(); node != null; node = node.getNext()) {
            ++count;
        }
        if (linkedList.getSize() != count) {
            throw new RuntimeException("Wrong count, linked: " + linkedList.getSize() + ", count: " + count);
        }
        int countBack = 0;
        for (LinkedList.Node<VboRange> nodeBack = linkedList.getLast(); nodeBack != null; nodeBack = nodeBack.getPrev()) {
            ++countBack;
        }
        if (linkedList.getSize() != countBack) {
            throw new RuntimeException("Wrong count back, linked: " + linkedList.getSize() + ", count: " + countBack);
        }
    }

    private static void checkLists(List<VboRange> listFree, List<VboRange> listUsed, int count) {
        int total = listFree.size() + listUsed.size();
        if (total != count) {
            throw new RuntimeException("Total size: " + total);
        }
    }

    private static void dbg(String str) {
        System.out.println(str);
    }
}

