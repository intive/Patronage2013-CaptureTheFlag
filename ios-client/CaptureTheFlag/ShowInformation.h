//
//  ShowInformation.h
//  CaptureTheFlag
//
//  Created by Milena Gnoińska on 15.04.2013.
//  Copyright (c) 2013 BLStream. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ShowInformation : NSObject

+ (void)showError:(NSString *)error;
+ (void)showMessage:(NSString *)message withTitle:(NSString *)title;

@end