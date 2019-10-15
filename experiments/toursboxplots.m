


fid=fopen('eil76_output.txt','r');
tline = fgetl(fid); %get the first line

X= [];
g={};

x=[];

while ischar(tline)
    strline = strtrim(string(tline));
%     fprintf('%s\n', strline);
    
    if endsWith(strline, 'txt')
       g{end+1}=char(shortlabel(strline));
       X=[X padcolumn(x)];
       x=[];
    else
       d = str2double(strsplit(strline)); 
       x=[x d];
    end
    
    
    tline = fgetl(fid);
end

X=[X padcolumn(x)];

fclose(fid);

boxplot(X, 'labels', g, 'LabelOrientation','inline');

ylabel('Reward');




function [c]=padcolumn(v)
    c = transpose(v);
    c = padarray(c, 200-length(c), NaN, 'post');
end

function [s]=shortlabel(l)
    a=strsplit(l, '.');
    b=strsplit(a(1), '_');
    s=strcat(upper(a(3)), '_', shorttype(b(3)), b(4));
end

function [t]=shorttype(s)
    if s=="bounded-strongly-corr"
        t="Bounded";
    elseif s=="uncorr"
        t="Uncorrelated";
    elseif s=="uncorr-similar-weights"
        t="SimilarWeights";
    end
end