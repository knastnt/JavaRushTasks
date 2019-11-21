package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
//    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    {
        for (int i = 0; i < table.length; i++) {
            table[i] = new FileBucket();
        }
    }
    private int size;
//    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
//    private float loadFactor = DEFAULT_LOAD_FACTOR;

    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize = Integer.MAX_VALUE;


    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }


    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
//        if (value == null)
//
//            return containsNullValue();


        FileBucket[] tab = table;

        for (int i = 0; i < tab.length ; i++) {
//            if(tab[i] == null) continue;
            for (Entry e = tab[i].getEntry(); e != null; e = e.next) {

                if (value.equals(e.value)) {

                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void put(Long key, String value) {

        int hash = hash((long)key.hashCode());

        int i = indexFor(hash, table.length);

//        if(table[i] == null){
//            Entry e = new Entry(hash, key, value, null);
//            table[i].putEntry(e);
//        }

        for (Entry e = table[i].getEntry(); e != null; e = e.next) {

            Object k;

            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {

                String oldValue = e.value;

                e.value = value;

            }

        }


        size++;

        addEntry(hash, key, value, i);

//        return null;
    }

    @Override
    public Long getKey(String value) {
        for (FileBucket bu : table) {
            Entry curEntry = bu.getEntry();
           do {
               if (curEntry.getValue().equals(value)) {
                   return curEntry.getKey();
               }
               curEntry = curEntry.next;
           } while(curEntry.next != null);
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        return getEntry(key).getValue();
    }

    public int hash(Long k){
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }
    public int indexFor(int hash, int length){
        return hash & (length-1);
    }
    public Entry getEntry(Long key){
        int hash = (key == null) ? 0 : hash((long)key.hashCode());

        for (Entry e = table[indexFor(hash, table.length)].getEntry(); e != null; e = e.next) {

            Object k;

            if (e.hash == hash &&

                    ((k = e.key) == key || (key != null && key.equals(k))))

                return e;

        }

        return null;
    }
    public void resize(int newCapacity){


        FileBucket[] oldTable = table;

        int oldCapacity = oldTable.length;

        if (oldCapacity == 1 << 30) {

            bucketSizeLimit = Integer.MAX_VALUE;

            return;

        }


        Entry[] newTable = new Entry[newCapacity];

        transfer(newTable);

//        table = newTable;
//
//        threshold = (int)(newCapacity * loadFactor);
    }
    public void transfer(Entry[] newTable){
        FileBucket[] src = table;

        int newCapacity = newTable.length;

        for (int j = 0; j < src.length; j++) {

            Entry e = src[j].getEntry();

            if (e != null) {

                src[j] = null;

                do {

                    Entry next = e.next;

                    int i = indexFor(e.hash, newCapacity);

                    e.next = newTable[i];

                    newTable[i] = e;

                    e = next;

                } while (e != null);

            }

        }
    }
    public void addEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex].getEntry();

        table[bucketIndex].putEntry( new Entry(hash, key, value, e) );

//        if (size++ >= threshold)
        if (bucketSizeLimit <= table[bucketIndex].getFileSize())
            resize(2 * table.length);
    }
    public void createEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex].getEntry();

        table[bucketIndex].putEntry(new Entry(hash, key, value, e));

        size++;
    }
}
