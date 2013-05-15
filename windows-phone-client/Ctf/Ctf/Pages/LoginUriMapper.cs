﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Navigation;

namespace Ctf.Pages
{
    public class LoginUriMapper : UriMapperBase
    {
        public override Uri MapUri(Uri uri)
        {
            if (uri.OriginalString == "/Pages/LaunchPage.xaml")
            {
                if (ApplicationSettings.Instance.HasLoginInfo() == false)
                {
                    uri = new Uri("/Pages/MainPage.xaml", UriKind.Relative);
                }
                else
                {
                    uri = new Uri("/Pages/LoggedIn.xaml", UriKind.Relative);
                }
            }
            return uri;
        }
    } 
}
