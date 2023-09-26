package Item13;

public class HashTable implements Cloneable{

    private Entry[] buckets;

    private static class Entry{
        final Object key;
        Object value;
        Entry next;

        Entry(Object key, Object value, Entry next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        // 해결책 적용 1
//        Entry deepCopy(){
//            return new Entry(key, value, next == null? null : next.deepCopy());
//        }

        // 해결책 적용 2
        Entry deepCopy(){
            Entry result = new Entry(key, value, next);
            for(Entry p=result; p.next!=null; p=p.next){
                p.next = new Entry(p.next.key, p.next.value, p.next.next);
            }
            return result;
        }
    }


    // 해결책 적용
    @Override
    public HashTable clone(){
        try{
            HashTable result = (HashTable) super.clone();
            // 복제본은 자신만의 버킷배열을 갖지만 이 배열은 원본과 같은 연결리스트를 참조하여 문제 야기
            result.buckets = new Entry[buckets.length];
            for(int i=0; i<buckets.length; i++){
                if(buckets[i] != null){
                    result.buckets[i] = buckets[i].deepCopy();
                }
            }
            return result;
        }
        catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
    }


//    @Override
//    public HashTable clone(){
//        try{
//            HashTable result = (HashTable) super.clone();
//            // 복제본은 자신만의 버킷배열을 갖지만 이 배열은 원본과 같은 연결리스트를 참조하여 문제 야기
//            result.buckets = buckets.clone();
//            return result;
//        }
//        catch (CloneNotSupportedException e){
//            throw new AssertionError();
//        }
//    }
}
