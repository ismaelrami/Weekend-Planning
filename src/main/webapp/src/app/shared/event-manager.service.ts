import { Observable, Observer, Subscription } from 'rxjs/Rx';
export declare class EventManager {
    observable: Observable<any>;
    observer: Observer<any>;
    constructor();
    broadcast(event: any): void;
    subscribe(eventName: any, callback: any): Subscription;
    destroy(subscriber: Subscription): void;
}
