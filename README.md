
In LiveData - Android Developer Documentation, you can see that for LiveData, setValue() & postValue() methods are not public.

Whereas, in MutableLiveData - Android Developer Documentation, you can see that, MutableLiveData extends LiveData internally and also the two magic methods of LiveData is publicly available in this and they are setValue() & postValue().

setValue() : set the value and dispatch the value to all the active observers, must be called from main thread.

postValue() : post a task to main thread to override value set by setValue(), must be called from background thread.

So, LiveData is immutable. MutableLiveData is LiveData which is mutable & thread-safe.
