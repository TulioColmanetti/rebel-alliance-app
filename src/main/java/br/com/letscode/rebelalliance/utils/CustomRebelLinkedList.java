package br.com.letscode.rebelalliance.utils;

import br.com.letscode.rebelalliance.domain.Rebel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomRebelLinkedList {
    private node head = null;
    private String orderBy = "";

    // node a, b;
    static class node {
        Rebel rebel;
        node next;

        node(Rebel rebel)
        {
            this.rebel = rebel;
        }
    }

    private node sortedMergeByName(node a, node b)
    {
        node result = null;
        /* Base cases */
        if (a == null)
            return b;
        if (b == null)
            return a;

        /* Pick either a or b, and recur */
        if (a.rebel.getName().compareTo(b.rebel.getName()) < 0) {
            result = a;
            result.next = sortedMergeByName(a.next, b);
        }
        else {
            result = b;
            result.next = sortedMergeByName(a, b.next);
        }
        return result;
    }

    private node sortedMergeByAge(node a, node b)
    {
        node result = null;
        /* Base cases */
        if (a == null)
            return b;
        if (b == null)
            return a;

        /* Pick either a or b, and recur */
        if (a.rebel.getAge() <= b.rebel.getAge()) {
            result = a;
            result.next = sortedMergeByAge(a.next, b);
        }
        else {
            result = b;
            result.next = sortedMergeByAge(a, b.next);
        }
        return result;
    }

    private node sortedMergeByRace(node a, node b)
    {
        node result = null;
        /* Base cases */
        if (a == null)
            return b;
        if (b == null)
            return a;

        /* Pick either a or b, and recur */
        if (a.rebel.getRace().ordinal() <= b.rebel.getRace().ordinal()) {
            result = a;
            result.next = sortedMergeByRace(a.next, b);
        }
        else {
            result = b;
            result.next = sortedMergeByRace(a, b.next);
        }
        return result;
    }

    public void mergeSort(){
        this.head = internalMergeSort(this.head);
    }

    private node internalMergeSort(node h)
    {
        // Base case : if head is null
        if (h == null || h.next == null) {
            return h;
        }

        // get the middle of the list
        node middle = getMiddle(h);
        node nextofmiddle = middle.next;

        // set the next of middle node to null
        middle.next = null;

        // Apply mergeSort on left list
        node left = internalMergeSort(h);

        // Apply mergeSort on right list
        node right = internalMergeSort(nextofmiddle);

        // Merge the left and right lists according to orderBy attribute
        node sortedlist = null;
        switch (orderBy.toUpperCase()){
            case "AGE":
                sortedlist = sortedMergeByAge(left, right);
                break;
            case "RACE":
                sortedlist = sortedMergeByRace(left, right);
                break;
            default:
                sortedlist = sortedMergeByName(left, right);
                break;
        }
        return sortedlist;
    }

    // Utility function to get the middle of the linked list
    private node getMiddle(node head)
    {
        if (head == null)
            return head;

        node slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void push(Rebel new_data)
    {
        /* allocate node */
        node new_node = new node(new_data);

        /* link the old list off the new node */
        new_node.next = head;

        /* move the head to point to the new node */
        head = new_node;
    }

    // Utility function to convert the linked list to String
    public String toString()
    {
        node headref = this.head;
        StringBuilder sb = new StringBuilder();
        while (headref != null) {
            sb.append(headref.rebel.toString() + "\n");
            headref = headref.next;
        }
        return sb.toString();
    }
}
